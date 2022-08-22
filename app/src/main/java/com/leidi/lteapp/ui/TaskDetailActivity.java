package com.leidi.lteapp.ui;

import android.content.Intent;

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

        findViewById(R.id.btn_arrive).setOnClickListener(view ->
                startActivity(new Intent(this,TaskRecordActivity.class ))
                );
    }
}
