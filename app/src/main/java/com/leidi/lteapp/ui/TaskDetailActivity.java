package com.leidi.lteapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.bean.TaskDetailBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 任务详情页面
 * @author 阎
 */
public class TaskDetailActivity extends BaseActivity {

    private int taskId;
    private TextView tvCreateTime, tvOverTime, tvTaskName, tvTaskContent, tvCreateBy, tvZy, tvBz, tvDw;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void initView() {
        setToolbar("任务详情");
        int type = getIntent().getIntExtra("type", 0);
        taskId = getIntent().getIntExtra("taskId", 0);

        tvCreateTime = findViewById(R.id.tv_create_time);
        tvOverTime = findViewById(R.id.tv_over_time);
        tvTaskName = findViewById(R.id.tv_task_name);
        tvTaskContent = findViewById(R.id.tv_task_content);
        tvCreateBy = findViewById(R.id.tv_task_person);
        tvZy = findViewById(R.id.tv_task_specialized);
        tvBz = findViewById(R.id.tv_task_group);
        tvDw = findViewById(R.id.tv_task_company);
        Button btn = findViewById(R.id.btn_arrive_site);

        if (type == 1) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
        }
        btn.setOnClickListener(v -> submitArrive());
        initData();
    }

    /**
     * 到达现场
     */
    private void submitArrive() {
        RxHttp.postForm(Url.task_arrive + taskId)
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                                .putExtra("taskId", taskId)
                        );
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> System.out.println(throwable.getMessage()));

    }

    private void initData() {
        //请求任务详情数据
        RxHttp.get(Url.task_detail + taskId)
                .asClass(TaskDetailBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        //将数据放到页面中
                        upDatePageContent(bean);
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> System.out.println(throwable.getMessage()));


    }

    private void upDatePageContent(TaskDetailBean bean) {
        tvCreateTime.setText(bean.getData().getCreateTime());
        tvOverTime.setText(bean.getData().getEndTime());
        tvTaskName.setText(bean.getData().getTaskName());
        tvTaskContent.setText(bean.getData().getTaskContent());
        tvCreateBy.setText(bean.getData().getCreateBy());
        tvZy.setText(bean.getData().getDeptName());
        tvBz.setText(bean.getData().getBzName());
        tvDw.setText(bean.getData().getDwName());
    }
}
