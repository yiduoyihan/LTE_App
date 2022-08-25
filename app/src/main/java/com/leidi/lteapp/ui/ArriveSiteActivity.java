package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * @author caiwu
 * @description 到达现场页面
 */
public class ArriveSiteActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_arrive_site;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("到达现场");
    }
}
