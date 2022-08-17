package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

/**
 * 检查更新页面
 *
 * @author caiwu
 */
public class CheckUpdateActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_update;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("检查更新");
        checkUpdate();
        TextView tvNoUpdate = findViewById(R.id.tv_check_update_1);
        View haveUpdate = findViewById(R.id.layout_have_update);
        TextView tvTitle = findViewById(R.id.tv_check_update_2);
        TextView tvVersion = findViewById(R.id.tv_check_update_3);
        Button btn = findViewById(R.id.btn_start_update);

        tvNoUpdate.setVisibility(View.VISIBLE);
    }

    /**
     * 检查是否有更新
     * */
    private void checkUpdate() {

    }
}
