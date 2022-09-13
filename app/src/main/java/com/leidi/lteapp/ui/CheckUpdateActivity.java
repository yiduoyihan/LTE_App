package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.UpdataBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import java.io.File;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 检查更新页面
 *
 * @author caiwu
 */
public class CheckUpdateActivity extends BaseActivity {
    //无更新时候的界面
    ImageView noUpdate;
    //有更新的界面
    View haveUpdate;
    //更新按钮
    Button updateBtn;
    //展示新版本的版本号
    TextView tvNewVersion;
    //新版本的url
    private String newApkUrl;
    //下载中的进度条
    ProgressBar progressBar;
    //下载中的提示文字
    TextView tvDwonloadNum;
    //显示下载中的dialog
    Dialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_update;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("更新");
        noUpdate = findViewById(R.id.iv_no_update);
        haveUpdate = findViewById(R.id.layout_have_new_version);
        updateBtn = findViewById(R.id.btn_update_now);
        tvNewVersion = findViewById(R.id.tv_new_version);

        checkUpdate();
        updateBtn.setOnClickListener(view -> downLoadNewApk());
    }

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void downLoadNewApk() {
        //开始下载的时候弹出对话框禁止操作并展示下载进度#24
        showDownloadDialog();
        String destPath = Environment.getExternalStorageDirectory() + "/Download/html/lte_gz.apk";
//        RxHttp.get("http://t.xiazaicc.com/001/3177")

        RxHttp.get(newApkUrl)
                .asAppendDownload(destPath, AndroidSchedulers.mainThread(), progress -> {
                    //下载进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    long currentSize = progress.getCurrentSize(); //当前已下载的字节大小
                    long totalSize = progress.getTotalSize();     //要下载的总字节大小
                    progressBar.setProgress(currentProgress);
                    tvDwonloadNum.setText("正在下载" + currentProgress + "%,请耐心等待");
                    if (currentProgress == 100) {
                        dialog.dismiss();
                    }
                }) //指定主线程回调
                .subscribe(s -> { //s为String类型
                    //下载成功，处理相关逻辑
                    //打开apk并提示安装
                    System.out.println();
                    startInstall(destPath);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    dialog.dismiss();
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });
    }

    private void showDownloadDialog() {
        @SuppressLint("InflateParams")
        View dialogView = LayoutInflater.from(this).inflate(R.layout.download_dialog, null);//解析我们自己写的布局
        dialog = new AlertDialog.Builder(this).create();//创建一个dialog
        dialog.show();//此处dialog应该先show然后再加载布局，否则会报错
        dialog.setContentView(dialogView);
        //初始化控件
        progressBar = dialogView.findViewById(R.id.progressBar);
        tvDwonloadNum = dialogView.findViewById(R.id.tv_download_num);
        dialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 开始安装apk
     */
    private void startInstall(String filePath) {
        //分别进行7.0以上和7.0以下的尝试
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7.0以上
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), "com.leidi.lteapp.fileprovider", apkfile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    /**
     * 检查是否有更新
     */
    private void checkUpdate() {
        loadingDialog.show();
        RxHttp.get(Url.check_update)
                .asClass(UpdataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    loadingDialog.closeSuccessAnim().loadSuccess();
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        if (bean.getData().getVersion().equals(String.valueOf(AppUtils.getAppVersionCode()))) {
                            //版本号一致，无更新
                            noUpdate.setVisibility(View.VISIBLE);
                        } else {
                            noUpdate.setVisibility(View.GONE);
                            haveUpdate.setVisibility(View.VISIBLE);
                            updateBtn.setVisibility(View.VISIBLE);
                            tvNewVersion.setText(bean.getData().getVersion());
                            newApkUrl = bean.getData().getUrl();
                        }
                    } else {
                        loadingDialog.closeFailedAnim().loadFailed();
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {
                    loadingDialog.closeFailedAnim().loadFailed();
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });

    }
}
