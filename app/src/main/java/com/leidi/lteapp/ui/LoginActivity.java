package com.leidi.lteapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.MainActivity;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.MyApp;
import com.leidi.lteapp.bean.LoginBean;
import com.leidi.lteapp.bean.UserInfoBean;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

public class LoginActivity extends BaseActivity {

    private EditText etAccount, etPassWord;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initView() {
        stateBarTransparent();
        // 判断是否登录过
        if (SPUtils.getInstance().getBoolean(SpUtilsKey.IS_LOGIN, false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            etAccount = findViewById(R.id.et_account);
            etPassWord = findViewById(R.id.et_pwd);
            findViewById(R.id.btn_login).setOnClickListener(view -> {
                if (verifyInputContent(etAccount) && verifyInputContent(etPassWord)) {
                    requestToLogin();
                }
            });
//            controlStateBar();
            //点击根布局隐藏软键盘
            controlKeyboard(R.id.layout_login);
        }
    }

    //请求登陆数据
    private void requestToLogin() {
        RxHttp.postForm(Url.login)
                .setAssemblyEnabled(false)
                .add("username", etAccount.getText().toString().trim())
                .add("password", etPassWord.getText().toString().trim())
                .asClass(LoginBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getCode() == 200) {
                        SPUtils.getInstance().put(SpUtilsKey.TOKEN, bean.getToken());
                        //获取用户信息
                        getUserInfo();
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                });
    }

    private void getUserInfo() {
        RxHttp.get(Url.getInfo)
                .asClass(UserInfoBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getCode() == 200) {
                    SPUtils.getInstance().put(SpUtilsKey.IS_LOGIN, true);
                    SPUtils.getInstance().put(SpUtilsKey.REMARK, bean.getUser().getRemark());
                    SPUtils.getInstance().put(SpUtilsKey.USER_NAME, bean.getUser().getUserName());
                    SPUtils.getInstance().put(SpUtilsKey.NICK_NAME, bean.getUser().getNickName());
                    SPUtils.getInstance().put(SpUtilsKey.PHONE_NO, bean.getUser().getPhonenumber());
                    SPUtils.getInstance().put(SpUtilsKey.HEAD_PIC, bean.getUser().getAvatar());
                    //请求成功跳转主页
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                });

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
