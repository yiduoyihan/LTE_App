package com.leidi.lteapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.ShowPicAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.TaskDetailBean;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.GridViewUtil;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.permissionx.guolindev.PermissionX;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import lte.trunk.telephony.CellEx;
import lte.trunk.telephony.TelephonyManagerEx;
import lte.trunk.telephony.TmoPhoneStateListenerEx;
import rxhttp.RxHttp;

/**
 * 任务详情页面
 *
 * @author 阎
 */
public class TaskDetailActivity extends BaseActivity {

    private int taskId;
    private TextView tvCreateTime, tvOverTime, tvTaskName, tvTaskContent, tvCreateBy, tvZy, tvBz, tvDw;
    //    String address = "";
    private String taskNo;
    private Button btnComplete;
    private Button btnArrive;
    int type;//1表示从未完成页面而来，2标示从已完成页面来。
    private String signId = "111";//小区编号
    private TelephonyManagerEx telephonyManagerEx;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_task_detail;
    }

    @Override
    protected void initView() {
        setToolbar("任务详情");
        type = getIntent().getIntExtra("type", 0);
        taskId = getIntent().getIntExtra("taskId", 0);

        tvCreateTime = findViewById(R.id.tv_create_time);
        tvOverTime = findViewById(R.id.tv_over_time);
        tvTaskName = findViewById(R.id.tv_task_name);
        tvTaskContent = findViewById(R.id.tv_task_content);
        tvCreateBy = findViewById(R.id.tv_task_person);
        tvZy = findViewById(R.id.tv_task_specialized);
        tvBz = findViewById(R.id.tv_task_group);
        tvDw = findViewById(R.id.tv_task_company);
        btnArrive = findViewById(R.id.btn_arrive_site);
        btnComplete = findViewById(R.id.btn_complete_task);
        btnComplete.setOnClickListener(v -> completeTask());
        if (type == 1) {
            btnArrive.setVisibility(View.VISIBLE);
        } else {
            btnArrive.setVisibility(View.GONE);
        }
        btnArrive.setOnClickListener(v -> submitArrive());
        initData();
//        getAreaId();
    }

//    private void getAreaId() {
//        PermissionX.init(this)
//                .permissions("lte.trunk.permission.READ_PHONE_STATE")
//                .onExplainRequestReason((scope, deniedList, beforeRequest) -> scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白"))
//                .onForwardToSettings((scope, deniedList) -> scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白"))
//                .request((allGranted, grantedList, deniedList) -> {
//                    if (allGranted) {
//                        telephonyManagerEx = TelephonyManagerEx.getDefault();
//                        telephonyManagerEx.listen(tmoPhoneStateListenerEx, TmoPhoneStateListenerEx.LISTEN_CELL_INFO);
//                        //获取小区位置信息
//                        telephonyManagerEx.requestCellInfo();
//                    } else {
//                        ToastUtils.showShort("您拒绝了如下权限：" + deniedList);
//                    }
//                });
//    }

//    private final TmoPhoneStateListenerEx tmoPhoneStateListenerEx = new TmoPhoneStateListenerEx() {
//        @Override
//        public void onCellInfoChanged(CellEx cellEx) {
//            super.onCellInfoChanged(cellEx);
//            signId = String.valueOf(cellEx.getCellId());
//        }
//    };

    private void completeTask() {
        RxHttp.postForm(Url.task_end + taskId)
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
        if (signId.isEmpty()) {
            ToastUtils.showShort("获取不到位置信息，不能提交");
            return;
        }
        RxHttp.postForm(Url.task_arrive + taskId)
                .add("arrivePosition", "")
                .add("arrivePositionNo", signId)
                .add("taskNo", getIntent().getStringExtra("taskNo"))
                .asResponse(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                            .putExtra("taskId", taskId)
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
        tvCreateTime.setText(bean.getCreateTime());
        tvOverTime.setText(bean.getEndTime());
        tvTaskName.setText(bean.getTaskName());
        tvTaskContent.setText(bean.getTaskContent());
        tvCreateBy.setText(bean.getCreateBy());
        tvZy.setText(bean.getDeptName());
        tvBz.setText(bean.getBzName());
        tvDw.setText(bean.getDwName());
        taskNo = bean.getTaskNo();

        if (bean.getCreateUserId().equals(String.valueOf(SPUtils.getInstance().getString(SpUtilsKey.USER_ID)))
                && type == 1) {
            btnComplete.setVisibility(View.VISIBLE);
        } else {
            btnComplete.setVisibility(View.GONE);
        }

        //如果状态为1，标示此任务已经被点过了到达现场
        if (null != bean.getAppLteTaskDetails() && null != bean.getAppLteTaskDetails().getStatus()) {
            if (bean.getAppLteTaskDetails().getStatus().equals("1")) {
                startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                        .putExtra("address", bean.getAppLteTaskDetails().getArrivePosition())
                        .putExtra("time", bean.getCreateTime())
                        .putExtra("taskId", taskId)
                        .putExtra("taskNo", taskNo));
                finish();
            } else {
                btnArrive.setVisibility(View.GONE);
            }
        }
        if (null != bean.getAppLteTaskDetails()) {
            //不为空，有数据标示这个任务有人完成过。将完成的内容显示出来
            findViewById(R.id.layout_submit_content).setVisibility(View.VISIBLE);
            TextView text1 = findViewById(R.id.tv_arrive_time);
            text1.setText(bean.getAppLteTaskDetails().getArriveTime());
            TextView text2 = findViewById(R.id.tv_address);
            text2.setText(bean.getAppLteTaskDetails().getArrivePosition());
            TextView text3 = findViewById(R.id.tv_complete_person);
            text3.setText(bean.getAppLteTaskDetails().getUserName());
            TextView text4 = findViewById(R.id.tv_question_description);
            text4.setText(bean.getAppLteTaskDetails().getFaultDes());
            TextView text5 = findViewById(R.id.tv_gc);
            text5.setText(bean.getAppLteTaskDetails().getProcessDes());
            TextView text6 = findViewById(R.id.tv_use_tools);
            text6.setText(bean.getAppLteTaskDetails().getDeviceDes());

            if (null != bean.getAppLteTaskDetails().getLteTaskDetailsPics()) {
                //如果有图片，把图片显示出来
                List<String> pathList = new ArrayList<>();
                for (int i = 0; i < bean.getAppLteTaskDetails().getLteTaskDetailsPics().size(); i++) {
                    pathList.add(bean.getAppLteTaskDetails().getLteTaskDetailsPics().get(i).getUrl());
                }
                ShowPicAdapter mAdapter = new ShowPicAdapter(this);
                GridView gridView = findViewById(R.id.gv_image_choose);
                mAdapter.setData(pathList);
                gridView.setAdapter(mAdapter);
                gridView.setLayoutParams(GridViewUtil.setGridViewHeightBasedOnChildren(gridView, 4));

                gridView.setOnItemClickListener((parent, view, position, id) ->
                        startActivity(new Intent(TaskDetailActivity.this, PreviewActivity.class)
                                .putExtra("position", position)
                                .putExtra("data", (Serializable) pathList)));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
