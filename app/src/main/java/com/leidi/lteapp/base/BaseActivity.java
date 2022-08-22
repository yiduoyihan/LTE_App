package com.leidi.lteapp.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.blankj.utilcode.util.KeyboardUtils;
import com.leidi.lteapp.R;


/**
 * @author yan
 * @description 基类
 */
public abstract class BaseActivity extends FragmentActivity {

    public ImageView tvTitleLeftButton;
    public ImageView tvTitleRightButton;
    TextView tvTitleCenter;
    Bundle savedInstanceState;
    TextView tvNetDisconnection;
    public LinearLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        // 竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        controlStateBar();
        setContentView(getLayoutId());
        tvTitleLeftButton = findViewById(R.id.tv_title_left);
        tvTitleCenter = findViewById(R.id.tv_title_center);
        tvTitleRightButton = findViewById(R.id.tv_title_right);
        tvTitleLeftButton.setOnClickListener(view -> onBackClick());
        initView();
    }

    /**
     * 对公共标题的初始化
     */
    protected void setToolbar(String str) {
        tvTitleCenter.setText(str);
    }

    protected void showToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    protected void hintToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    /**
     * 控制标题显示的内容，左边按钮和右边按钮是否显示
     */
    protected void setToolbar(int left, String str, int right) {
        tvTitleLeftButton.setVisibility(left);
        tvTitleRightButton.setVisibility(right);
        tvTitleCenter.setText(str);
    }

    /**
     * 控制键盘收起
     */
    protected void controlKeyboard(int id) {
        findViewById(id).setOnClickListener(KeyboardUtils::hideSoftInput);
    }

    protected void controlStateBar() {
        //21表示5.0
        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        decorView.setSystemUiVisibility(option);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    protected void stateBarTransparent(){
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    protected Bundle getSavedInstanceState() {
        return savedInstanceState;
    }


    /**
     * 添加布局
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    protected void onBackClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
