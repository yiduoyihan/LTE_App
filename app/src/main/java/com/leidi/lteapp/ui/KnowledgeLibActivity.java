package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class KnowledgeLibActivity extends BaseActivity {

    EditText etInput;
    Button btnSearch;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_lib;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("知识库");
        etInput = findViewById(R.id.et_knowledge_search);
        btnSearch = findViewById(R.id.btn_knowledge_search);
        btnSearch.setOnClickListener(v -> {
            if (etInput.getText().toString().trim().isEmpty()) {
                ToastUtils.showShort("请输入您想要查询的内容");
            } else {
                requestToSearch();
            }
        });
    }

    /**
     * 请求服务器查询搜索内容
     */
    private void requestToSearch() {
        RxHttp.get(Url.knowledge)
                .add("fileName", etInput.getText().toString().trim())
//                          .asClass(BaseBean.class)
                .asString()
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    System.out.println("====" + bean);
//                              if (bean.getCode() == Constant.SUCCESS_CODE) {
//                              } else {
//                                  ToastUtils.showShort(bean.getMsg());
//                              }
                }, throwable -> {
                });

    }
}
