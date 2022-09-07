package com.leidi.lteapp.ui;


import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.SignMsgBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.Url;
import com.permissionx.guolindev.PermissionX;
import com.rxjava.rxlife.RxLife;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class SignActivity extends BaseActivity {

    TextView tvStart, tvEnd, tvTimeStart, tvTimeEnd;
    View tvSignStart, tvSignEnd;
    public LocationClient mLocationClient = null;
    private final MyLocationListener myListener = new MyLocationListener();
    String address;

    //在主线程里面处理消息并更新UI界面
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                long sysTime = System.currentTimeMillis();
                CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", sysTime);
                tvTimeEnd.setText(sysTimeStr); //更新时间
                tvTimeStart.setText(sysTimeStr); //更新时间
            }
        }
    };
    Timer timer = new Timer();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("签到");
        initBdLocation();
        requestLocationPermission();
        getLastSignTime();
        tvStart = findViewById(R.id.tv_sign_time_start);
        tvEnd = findViewById(R.id.tv_sign_time_end);
        tvSignStart = findViewById(R.id.tv_sign_start_btn);
        tvTimeStart = findViewById(R.id.tv_show_start_time);
        tvSignEnd = findViewById(R.id.tv_sign_end_btn);
        tvTimeEnd = findViewById(R.id.tv_show_end_time);
        tvSignStart.setOnClickListener(v -> requestSignStart());

        tvSignEnd.setOnClickListener(v -> requestSignEnd());

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;  //消息(一个整型值)
                mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
            }
        }, 1, 1000);

    }

    /**
     * 获取最后一次签到
     */
    private void getLastSignTime() {
        RxHttp.get(Url.sign_last)
                .asClass(SignMsgBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        if (null != bean.getData()) {
                            //没有数据 证明是一次新的打卡流程
                            tvStart.setText(bean.getData().getWorkStartTime()); //更新时间
                        }
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                });
    }

    /**
     * 上班签到
     */
    private void requestSignStart() {
        if (address.isEmpty()) {
            ToastUtils.showShort("没有获取到位置信息，不能签到");
            return;
        }
        RxHttp.postBody(Url.sign_start)
                .setBody(getStartElement())
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        long sysTime = System.currentTimeMillis();
                        CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", sysTime);
                        tvStart.setText(sysTimeStr); //更新时间
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {
                });


    }


    /**
     * 下班签到
     */
    private void requestSignEnd() {
        if (address.isEmpty()) {
            ToastUtils.showShort("没有获取到位置信息，不能签到");
            return;
        }
        RxHttp.putBody(Url.sign_end)
                .setBody(getEndElement())
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        long sysTime = System.currentTimeMillis();
                        CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", sysTime);
                        tvEnd.setText(sysTimeStr); //更新时间
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {

                });


    }

    /**
     * 组装请求体
     */
    private JsonElement getStartElement() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("inAreaName", address);
            jsonObject.put("intAreaNo", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //通过gson转一下解决JSONObject会自动嵌套一层nameValuePairs的问题
        Gson gson = new Gson();
        return gson.fromJson(String.valueOf(jsonObject), JsonElement.class);
    }

    private JsonElement getEndElement() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("outAreaName", address);
            jsonObject.put("outAreaNo", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //通过gson转一下解决JSONObject会自动嵌套一层nameValuePairs的问题
        Gson gson = new Gson();
        return gson.fromJson(String.valueOf(jsonObject), JsonElement.class);
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

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取详细地址信息
            address = location.getAddrStr().replace("中国", "");
            System.out.println("========" + address);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        mLocationClient.stop();
    }
}


