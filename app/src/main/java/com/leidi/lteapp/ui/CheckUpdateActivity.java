package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    ImageView noUpdate;
    View haveUpdate;
    Button updateBtn;
    TextView tvNewVersion;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_update;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("更新");
        checkUpdate();
        noUpdate = findViewById(R.id.iv_no_update);
        haveUpdate = findViewById(R.id.layout_have_new_version);
        updateBtn = findViewById(R.id.btn_update_now);
        tvNewVersion = findViewById(R.id.tv_new_version);

        noUpdate.setVisibility(View.VISIBLE);

        checkUpdate();
    }

    /**
     * 检查是否有更新
     * */
    private void checkUpdate() {

    }
}
