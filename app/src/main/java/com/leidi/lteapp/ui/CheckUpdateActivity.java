package com.leidi.lteapp.ui;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.UpdataBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.DownLoadUtil;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    TextView tvDownloadNum;
    //显示下载中的dialog
    Dialog dialog;
    String TAG = "TEST";

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
//        DownLoadUtil.sendRequestWithOkHttp(this, newApkUrl,Constant.SAVE_PATH, loadingDialog);
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
                    if (null == bean.getData() || bean.getData().getVersion().equals(AppUtils.getAppVersionName())) {
                        //版本号一致，无更新
                        noUpdate.setVisibility(View.VISIBLE);
                    } else {
                        noUpdate.setVisibility(View.GONE);
                        haveUpdate.setVisibility(View.VISIBLE);
                        updateBtn.setVisibility(View.VISIBLE);
                        tvNewVersion.setText(bean.getData().getVersion());
                        newApkUrl = bean.getData().getUrl();
                    }

                }, throwable -> {
                    loadingDialog.closeFailedAnim().loadFailed();
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });
    }

}