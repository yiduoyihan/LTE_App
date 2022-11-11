package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.SignMsgBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
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
    TextView tvTest;
    EditText etBz;//备注
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
        getLastSignTime();
        tvStart = findViewById(R.id.tv_sign_time_start);
        tvEnd = findViewById(R.id.tv_sign_time_end);
        tvSignStart = findViewById(R.id.tv_sign_start_btn);
        tvTimeStart = findViewById(R.id.tv_show_start_time);
        tvSignEnd = findViewById(R.id.tv_sign_end_btn);
        tvTimeEnd = findViewById(R.id.tv_show_end_time);
        tvSignStart.setOnClickListener(v -> requestSignStart());
        tvTest = findViewById(R.id.tvTest);
        etBz = findViewById(R.id.et_bz);

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
                            tvStart.setText(String.format("%s\n%s", bean.getData().getWorkStartTime(), bean.getData().getRemark())); //更新时间
                        }
                    }
                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));
    }

    /**
     * 上班签到
     */
    private void requestSignStart() {
        if (getSignId().isEmpty()) {
            ToastUtils.showShort("没有获取到小区信息，不能签到");
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
                        CharSequence sysTimeStr = DateFormat.format("yyyy:MM:dd HH:mm:ss", sysTime);
                        tvStart.setText(String.format("%s\n%s", sysTimeStr, etBz.getText().toString().trim())); //更新时间
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));

    }


    /**
     * 下班签到
     */
    private void requestSignEnd() {
        if (getSignId().isEmpty()) {
            ToastUtils.showShort("没有获取到小区信息，不能签到");
            return;
        }
        RxHttp.putBody(Url.sign_end)
                .setBody(getEndElement())
                .asResponse(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("yyyy:MM:dd HH:mm:ss", sysTime);
                    tvEnd.setText(String.format("%s\n%s", sysTimeStr, etBz.getText().toString().trim())); //更新时间
                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));

    }

    /**
     * 组装请求体
     */
    private JsonElement getStartElement() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("inAreaName", "");
            jsonObject.put("inAreaNo", getSignId());
            jsonObject.put("remark", etBz.getText().toString().trim());
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
            jsonObject.put("outAreaName", "");
            jsonObject.put("outAreaNo", getSignId());
            jsonObject.put("remark", etBz.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //通过gson转一下解决JSONObject会自动嵌套一层nameValuePairs的问题
        Gson gson = new Gson();
        return gson.fromJson(String.valueOf(jsonObject), JsonElement.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}


