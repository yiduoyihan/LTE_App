package com.leidi.lteapp.ui;

import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * 创建任务单页面
 */
public class CreateTaskActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_task;
    }

    protected void initView() {
        setToolbar("创建任务单");
        TextView tv1 = findViewById(R.id.tv_create_task_start_time);
        TextView tv2 = findViewById(R.id.tv_create_task_person);
        TextView tv3 = findViewById(R.id.tv_create_task_group);
        TextView tv4 = findViewById(R.id.tv_create_task_specialized);
        TextView tv5 = findViewById(R.id.tv_create_task_company);
        EditText et1 = findViewById(R.id.et_create_task_name);
        EditText et2 = findViewById(R.id.et_create_task_content);
        findViewById(R.id.btn_create_task).setOnClickListener(view -> {
            ToastUtils.showShort("创建任务");
        });
    }
}
