package com.leidi.lteapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

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
        int type = getIntent().getIntExtra("type", 0);
        Button btn = findViewById(R.id.btn_arrive_site);
        if (type == 1) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class));
            }
        });

    }
}
