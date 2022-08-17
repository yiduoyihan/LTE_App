package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class KnowledgeLibActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_lib;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("知识库");
//        TextView tvVersion = findViewById(R.id.tv_version);
//        tvVersion.setText(String.format("%s%s", "版本号", AppUtils.getAppVersionName()));
    }
}
