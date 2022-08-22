package com.leidi.lteapp.ui;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
/**
 * 任务过程记录页面
 * */
public class TaskRecordActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_task;
    }

    @Override
    protected void initView() {
        setToolbar("任务记录");
        findViewById(R.id.btn_over_task).setOnClickListener(view -> {
            ToastUtils.showShort("任务完成");
            finish();
        });

        controlKeyboard(R.id.layout_record_task);
    }
}
