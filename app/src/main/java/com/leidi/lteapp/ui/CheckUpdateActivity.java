package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 检查更新页面
 *
 * @author caiwu
 */
public class CheckUpdateActivity extends BaseActivity {

    ImageView noUpdate;
    View haveUpdate;
    Button updateBtn;
    TextView tvNewVersion;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_update;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("更新");
        checkUpdate();
        noUpdate = findViewById(R.id.iv_no_update);
        haveUpdate = findViewById(R.id.layout_have_new_version);
        updateBtn = findViewById(R.id.btn_update_now);
        tvNewVersion = findViewById(R.id.tv_new_version);

        noUpdate.setVisibility(View.VISIBLE);

        checkUpdate();
    }

    /**
     * 检查是否有更新
     */
    private void checkUpdate() {
        RxHttp.postForm(Url.check_update)
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        ToastUtils.showShort("有更新");
                        noUpdate.setVisibility(View.GONE);
                        haveUpdate.setVisibility(View.VISIBLE);
                        updateBtn.setVisibility(View.VISIBLE);
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {
                });

    }
}
