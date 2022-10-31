package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.UpdataBean;
import com.leidi.lteapp.util.DownLoadUtil;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * @author caiwu
 */
public class AboutOursActivity extends BaseActivity {

    //更新按钮
    Button updateBtn;
    //新版本的url
    private String newApkUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_ours;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("关于");
        TextView tvVersion = findViewById(R.id.tv_version);
        tvVersion.setText(String.format("%s%s", "V.", AppUtils.getAppVersionName()));

        updateBtn = findViewById(R.id.btn_update_now);

        updateBtn.setOnClickListener(view -> {

            if (updateBtn.getText().toString().trim().equals("检查更新")) {
                checkUpdate();
            } else if (updateBtn.getText().toString().trim().equals("立即更新")) {
                downLoadNewApk();
            }else {
                ToastUtils.showShort("已经是最新版本了");
            }
        });
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
                    if (null == bean.getData()
                            || bean.getData().getVersion().equals(AppUtils.getAppVersionName())
                            || Double.parseDouble(bean.getData().getVersion()) <= Double.parseDouble(AppUtils.getAppVersionName())) {
                        //版本号一致，无更新
                        updateBtn.setText("暂无更新");
                        ToastUtils.showShort("已经是最新版本了");
                    } else {
                        updateBtn.setText("立即更新");
                        newApkUrl = bean.getData().getUrl();
                    }
                }, throwable -> {
                    loadingDialog.closeFailedAnim().loadFailed();
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });
    }


    @SuppressLint("SetTextI18n")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void downLoadNewApk() {
        //开始下载的时候弹出对话框禁止操作并展示下载进度#24
        DownLoadUtil.sendRequestWithOkHttp(this, newApkUrl, loadingDialog);
    }
}
