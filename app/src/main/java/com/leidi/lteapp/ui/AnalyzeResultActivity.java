package com.leidi.lteapp.ui;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

public class AnalyzeResultActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_analyze_result;
    }

    @Override
    protected void initView() {
        setToolbar("诊断结果");
    }
}
