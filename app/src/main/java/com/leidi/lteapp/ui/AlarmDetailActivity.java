package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.widget.TextView;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.AlarmListBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author caiwu
 * @description 告警详情
 */
public class AlarmDetailActivity extends BaseActivity {

    private TextView tvTime,tvAlarmLv,tvAddress,tvType,tvDeviceName;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_alarm_detail;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("详情");
        tvTime = findViewById(R.id.tv_alarm_time);
        tvAlarmLv = findViewById(R.id.tv_alarm_lv);
        tvAddress = findViewById(R.id.tv_address_message);
        tvType = findViewById(R.id.tv_alarm_type);
        tvDeviceName = findViewById(R.id.tv_alarm_device_name);

        //粘性事件注册后立即收到消息
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void StickyEvent(AlarmListBean.RowsBean event){

        tvTime.setText(event.getOccurTime());
        tvDeviceName.setText(event.getDeviceName());
        tvAlarmLv.setText(event.getAlarmLevel()+"级");
        tvAddress.setText(event.getDevLocation());
        tvType.setText(event.getAlarmCause());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
