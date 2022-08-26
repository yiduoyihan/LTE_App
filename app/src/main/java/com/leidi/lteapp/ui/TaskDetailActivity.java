package com.leidi.lteapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

public class TaskDetailActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void initView() {
        setToolbar("任务详情");
        //TODO 还需要传递taskID过来查询详情
        int type = getIntent().getIntExtra("type", 0);
        Button btn = findViewById(R.id.btn_arrive_site);
        if (type == 1) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 到达现场先发送数据还是说先跳转页面 有待于进一步沟通
//                submitArrive();
                startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class));
            }
        });
        initData();
    }

    /**
     * 到达现场
     * */
    private void submitArrive() {
          RxHttp.postForm(Url.task_arrive)
                          .add("taskId", 1)
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

    private void initData() {
        //请求任务详情数据
        RxHttp.get(Url.task_detail)
                .add("taskId", 1)
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        //将数据放到页面中
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                    System.out.println(throwable.getMessage());
                });


    }
}
