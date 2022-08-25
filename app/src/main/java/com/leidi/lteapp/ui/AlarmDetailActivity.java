package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * @author caiwu
 * @description 告警详情
 */
public class AlarmDetailActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_alarm_detail;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("详情");

    }
}
