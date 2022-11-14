package com.leidi.lteapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.TaskDetailAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.ArriveBean;
import com.leidi.lteapp.bean.TaskDetailBean;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 任务详情页面
 *
 * @author 阎
 */
public class TaskDetailActivity extends BaseActivity {

    String[] strType = {"任务类型", "应急", "故障", "打卡", "巡检", "演练"};
    private int taskId;
    private View layoutStart, layoutEnd;
    private TextView tvCreateTime, tvOverTime, tvPlanStartTime, tvPlanOverTime, tvTaskType, tvTaskName, tvTaskContent, tvCreateBy, tvZy, tvBz, tvDw;
    private String taskNo;
    private Button btnArrive;
    int type;//1表示从未完成页面而来，2标示从已完成页面来。
    RecyclerView recyclerView;
    TaskDetailAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void initView() {
        setToolbar("任务详情");
        type = getIntent().getIntExtra("type", 0);
        taskId = getIntent().getIntExtra("taskId", 0);

        btnArrive = findViewById(R.id.btn_arrive_site);
        Button btnComplete = findViewById(R.id.btn_complete_task);
        btnComplete.setOnClickListener(v -> completeTask());
        if (type == 2) {
            btnArrive.setVisibility(View.GONE);
            btnComplete.setVisibility(View.GONE);
        } else {
            btnArrive.setVisibility(View.VISIBLE);
        }
        btnArrive.setOnClickListener(v -> submitArrive());

        initRecyclerView();
        initData();
    }

    private void initRecyclerView() {
        //数据适配器
        adapter = new TaskDetailAdapter(this);
        adapter.setAnimationEnable(true);
        //列表
        recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // 头部控件
        View headView = View.inflate(this, R.layout.task_detail_head, null);
        initHead(headView);
        adapter.addHeaderView(headView);
        // 尾部占位控件
        View footerView = View.inflate(this, R.layout.task_detail_footer, null);
        adapter.addFooterView(footerView);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            TaskDetailBean.DataBean.AppLteTaskDetailsBean bean = (TaskDetailBean.DataBean.AppLteTaskDetailsBean) adapter.getData().get(position);
            if (bean.getStatus().equals("2")) {
                //已完成的任务不跳转
                return;
            } else if (!bean.getUserName().equals(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME))) {
                //不是自己执行的任务不跳转
                return;
            } else if (type == 2) {
                //从已结束页面过来的不执行跳转操作
                return;
            }

            startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                    .putExtra("address", bean.getArrivePosition())
                    .putExtra("time", bean.getArriveTime())
                    .putExtra("taskId", taskId)
                    .putExtra("detailsId", bean.getId())
                    .putExtra("signId", getSignId())
                    .putExtra("taskNo", taskNo));
        });
    }

    private void initHead(View headView) {
        tvCreateTime = headView.findViewById(R.id.tv_create_time);
        tvOverTime = headView.findViewById(R.id.tv_over_time);
        tvPlanStartTime = headView.findViewById(R.id.tv_detail_plan_start_time);
        tvPlanOverTime = headView.findViewById(R.id.tv_detail_plan_over_time);
        tvTaskType = headView.findViewById(R.id.tv_task_type);
        tvTaskName = headView.findViewById(R.id.tv_task_name);
        tvTaskContent = headView.findViewById(R.id.tv_task_content);
        tvCreateBy = headView.findViewById(R.id.tv_task_person);
        tvZy = headView.findViewById(R.id.tv_task_specialized);
        tvBz = headView.findViewById(R.id.tv_task_group);
        tvDw = headView.findViewById(R.id.tv_task_company);
        layoutStart = headView.findViewById(R.id.layout_start_time);
        layoutEnd = headView.findViewById(R.id.layout_end_time);
    }

    /**
     * 完成任务
     */
    private void completeTask() {
        RxHttp.postForm(Url.task_end + taskId)
                .add("endPositionNo", getSignId())
                .add("endPosition", "")
                .asResponse(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    ToastUtils.showShort(bean);
                    EventBus.getDefault().post(new RefreshTaskDoingEvent());
                    finish();
                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));
    }

    /**
     * 到达现场
     */
    private void submitArrive() {
        if (getSignId().isEmpty()) {
            ToastUtils.showShort("获取不到位置信息，不能提交");
            return;
        }
        RxHttp.postForm(Url.task_arrive + taskId)
                .add("arrivePosition", "")
                .add("arrivePositionNo", getSignId())
                .add("taskNo", getIntent().getStringExtra("taskNo"))
                .asResponse(ArriveBean.DataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                            .putExtra("address", bean.getArrivePosition())
                            .putExtra("time", bean.getArriveTime())
                            .putExtra("taskId", taskId)
                            .putExtra("detailsId", bean.getId())
                            .putExtra("signId", getSignId())
                            .putExtra("taskNo", taskNo));
                    finish();
                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));

    }

    private void initData() {
        //请求任务详情数据
        RxHttp.get(Url.task_detail + taskId)
                .asResponse(TaskDetailBean.DataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(this::upDatePageContent, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));

    }

    /**
     * 将数据放到页面中
     */
    private void upDatePageContent(TaskDetailBean.DataBean bean) {
        if (null != bean.getTaskType() && Integer.parseInt(bean.getTaskType()) < 3) {
            //应急+故障，需要显示开始时间  结束时间
            layoutStart.setVisibility(View.VISIBLE);
            layoutEnd.setVisibility(View.VISIBLE);
            tvPlanStartTime.setText(bean.getPlanCompleteStartTime());
            tvPlanOverTime.setText(bean.getPlanCompleteEndTime());
            tvTaskType.setText(strType[Integer.parseInt(bean.getTaskType())]);
        }
        tvOverTime.setText(bean.getEndTime());
        tvCreateTime.setText(bean.getCreateTime());
        tvTaskName.setText(bean.getTaskName());
        tvTaskContent.setText(bean.getTaskContent());
        tvCreateBy.setText(bean.getCreateBy());
        tvZy.setText(bean.getDeptName());
        tvBz.setText(bean.getBzName());
        tvDw.setText(bean.getDwName());
        taskNo = bean.getTaskNo();

        //循环判断任务里面是否有未完成，如果有，ID是否和自己相同
        if (null != bean.getAppLteTaskDetails() && bean.getAppLteTaskDetails().size() > 0) {
            for (int i = 0; i < bean.getAppLteTaskDetails().size(); i++) {
                if (bean.getAppLteTaskDetails().get(i).getStatus().equals("1")) {
                    if (bean.getAppLteTaskDetails().get(i).getUserName().equals(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME))) {
                        btnArrive.setVisibility(View.GONE);
                    }
                }
            }
        }

        if (null != bean.getAppLteTaskDetails()) {
            adapter.setList(bean.getAppLteTaskDetails());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
