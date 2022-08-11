package com.leidi.lteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leidi.lteapp.MainActivity;
import com.leidi.lteapp.R;
import com.leidi.lteapp.util.KeyboardUtils;
import com.leidi.lteapp.util.ToastUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText etAccount, etPassWord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        etAccount = findViewById(R.id.et_account);
        etPassWord = findViewById(R.id.et_pwd);
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            if (verifyInputContent(etAccount) && verifyInputContent(etPassWord)) {
                requestToLogin();
            }
        });
        //点击根布局隐藏软键盘
        findViewById(R.id.layout_login).setOnClickListener(KeyboardUtils::hideSoftInput);
    }

    //请求登陆数据
    private void requestToLogin() {

        //请求成功跳转主页
        startActivity(new Intent(this, MainActivity.class));
    }

    //账号密码的非空验证
    private boolean verifyInputContent(EditText view) {
        if (view.getText().toString().trim().isEmpty()) {
            ToastUtils.showShort("账号密码不能为空");
            return false;
        } else {
            return true;
        }
    }
}
