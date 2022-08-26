package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
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
 * @author caiwu
 * @description 到达现场页面
 */
public class ArriveSiteActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_arrive_site;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("到达现场");

        findViewById(R.id.btn_task_over).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               submitTaskProcess();
            }
        });
    }

    /**
     * 提交任务处理流程
     * */
    private void submitTaskProcess() {
          RxHttp.postForm(Url.task_complete)
                          .add("type", 1)
                          .asClass(BaseBean.class)
                          .observeOn(AndroidSchedulers.mainThread())
                          .to(RxLife.to(this))
                          .subscribe(bean -> {
                              //请求成功
                              if (bean.getCode() == Constant.SUCCESS_CODE) {
                              } else {
                                  ToastUtils.showShort(bean.getMsg());
                              }
                          }, throwable -> {
                              System.out.println(throwable.getMessage());
                          });


    }
}
