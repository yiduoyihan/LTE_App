package com.leidi.lteapp.ui;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.bean.TaskDetailBean;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.permissionx.guolindev.PermissionX;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 任务详情页面
 *
 * @author 阎
 */
public class TaskDetailActivity extends BaseActivity {

    private int taskId;
    private TextView tvCreateTime, tvOverTime, tvTaskName, tvTaskContent, tvCreateBy, tvZy, tvBz, tvDw;
    public LocationClient mLocationClient = null;
    private final MyLocationListener myListener = new MyLocationListener();
    String address;
    private String taskNo;
    private Button btnComplete;
    int type;
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
        Button btn = findViewById(R.id.btn_arrive_site);
        btnComplete = findViewById(R.id.btn_complete_task);
        btnComplete.setOnClickListener(v -> completeTask());
        if (type == 1) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
        }
        btn.setOnClickListener(v -> submitArrive());
        initData();

        initBdLocation();
        requestLocationPermission();
    }

    private void completeTask() {
        RxHttp.postForm(Url.task_end+taskId)
//                .add("taskId", taskId)
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    ToastUtils.showShort(bean.getMsg());
                    if (bean.getCode() == 200) {
                        EventBus.getDefault().post(new RefreshTaskDoingEvent());
                        finish();
                    }
                }, throwable -> {
                });
    }

    /**
     * 请求定位相关的权限
     */
    private void requestLocationPermission() {
        PermissionX.init(this)
                .permissions(Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .onExplainRequestReason((scope, deniedList, beforeRequest) -> scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白"))
                .onForwardToSettings((scope, deniedList) -> scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白"))
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        mLocationClient.start();
                    } else {
                        ToastUtils.showShort("您拒绝了如下权限：" + deniedList);
                    }
                });

    }

    /**
     * 百度定位
     */
    private void initBdLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);

        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setNeedNewVersionRgc(true);
        option.setScanSpan(5000);
        //可选，设置是否需要最新版本的地址信息。默认需要，即参数为true
        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

    /**
     * 到达现场
     */
    private void submitArrive() {
        RxHttp.postForm(Url.task_arrive + taskId)
                .add("arrivePosition", address.replace("中国", ""))
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                                .putExtra("taskNo", taskNo));
                        finish();
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
        if (bean.getData().getCreateBy().equals(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME))
                && type ==1) {
            btnComplete.setVisibility(View.VISIBLE);
        } else {
            btnComplete.setVisibility(View.GONE);
        }
        tvCreateTime.setText(bean.getData().getCreateTime());
        tvOverTime.setText(bean.getData().getEndTime());
        tvTaskName.setText(bean.getData().getTaskName());
        tvTaskContent.setText(bean.getData().getTaskContent());
        tvCreateBy.setText(bean.getData().getCreateBy());
        tvZy.setText(bean.getData().getDeptName());
        tvBz.setText(bean.getData().getBzName());
        tvDw.setText(bean.getData().getDwName());
        taskNo = bean.getData().getTaskNo();
        if (null != bean.getData().getAppLteTaskDetails().getStatus()) {
            if (bean.getData().getAppLteTaskDetails().getStatus().equals("1")) {
                startActivity(new Intent(TaskDetailActivity.this, ArriveSiteActivity.class)
                        .putExtra("taskNo", taskNo));
                finish();
            }
        }
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取详细地址信息
            address = location.getAddrStr();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
