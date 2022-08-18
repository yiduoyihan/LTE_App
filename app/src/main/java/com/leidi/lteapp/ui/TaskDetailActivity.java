package com.leidi.lteapp.ui;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

public class TaskDetailActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void initView() {
        setToolbar("任务详情");
//        controlStateBar();
    }
}
