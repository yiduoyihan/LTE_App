package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BusUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class SignActivity extends BaseActivity {

    TextView tvStart, tvEnd, tvTime;
    View tvSign;
    SimpleDateFormat formatter;
    Thread thread;

    //在主线程里面处理消息并更新UI界面
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("HH:mm:ss", sysTime);
                    tvTime.setText(sysTimeStr); //更新时间
                    break;
            }
        }
    };
    Timer timer = new Timer();

    @Override
    protected int getLayoutId() {
        BusUtils.register(this);
        return R.layout.activity_sign;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("签到");
        tvStart = findViewById(R.id.tv_sign_time_start);
        tvEnd = findViewById(R.id.tv_sign_time_end);
        tvSign = findViewById(R.id.tv_sign_btn);
        tvTime = findViewById(R.id.tv_show_time);

        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvStart.getText().toString().isEmpty()) {
                    tvStart.setText(tvTime.getText().toString());
                    BusUtils.post("time",tvTime.getText().toString());
                }
            }
        });

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;  //消息(一个整型值)
                mHandler.sendMessage(msg);// 每隔1秒发送一个msg给mHandler
            }
        }, 1, 1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        BusUtils.unregister(this);
    }
}


