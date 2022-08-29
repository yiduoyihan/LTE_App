package com.leidi.lteapp.ui;

import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 设置（即修改密码界面）
 *
 * @author ygq
 */
public class SettingActivity extends BaseActivity {

    EditText etOldPwd, etNewPwd1, etNewPwd2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        setToolbar("修改密码");
        controlKeyboard(R.id.layout_setting);
        etOldPwd = findViewById(R.id.et_setting_old_pwd);
        etNewPwd1 = findViewById(R.id.et_setting_new_pwd1);
        etNewPwd2 = findViewById(R.id.et_setting_new_pwd2);
        findViewById(R.id.btn_setting_save_pwd).setOnClickListener(view -> {
            if (verifyInputContent(etOldPwd) && verifyInputContent(etNewPwd1) && verifyInputContent(etNewPwd2)) {
                if (verifyTwiceInputIsSame(etNewPwd1.getText().toString().trim(), etNewPwd2.getText().toString().trim())) {
                    submitChangePwdRequest(etOldPwd.getText().toString().trim(), etNewPwd1.getText().toString());
                } else {
                    ToastUtils.showShort("两次新密码不一致，请检查");
                }
            } else {
                ToastUtils.showShort("请完整填写内容");
            }
        });
    }

    /**
     * 发送请求修改密码
     */
    private void submitChangePwdRequest(String oldPwd, String newPwd) {
        RxHttp.postForm(Url.change_pwd)
                .add("", "")
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == 200) {
                        ToastUtils.showShort("密码修改成功，请重新登录");
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                });


    }


    /**
     * 账号密码的非空验证
     */
    private boolean verifyInputContent(EditText view) {
        return !view.getText().toString().trim().isEmpty();
    }

    /**
     * 验证两次输入是否一致
     */
    private boolean verifyTwiceInputIsSame(String str1, String str2) {
        return str1.equals(str2);
    }
}
