package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class SignActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("签到");
//        TextView tvVersion = findViewById(R.id.tv_version);
//        tvVersion.setText(String.format("%s%s", "版本号", AppUtils.getAppVersionName()));
    }
}
