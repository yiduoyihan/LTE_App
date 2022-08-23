package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * @author caiwu
 */
public class AboutOursActivity extends BaseActivity {
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
    }
}
