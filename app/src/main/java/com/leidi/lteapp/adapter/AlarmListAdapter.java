package com.leidi.lteapp.adapter;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.AlarmListBean;
import com.leidi.lteapp.bean.TaskListBean;
import com.leidi.lteapp.view.CustomLoadMoreView;

import java.util.List;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class AlarmListAdapter extends BaseQuickAdapter<AlarmListBean.RowsBean, BaseViewHolder> implements LoadMoreModule {

    public AlarmListAdapter() {
        super(R.layout.item_alarm);
        addChildClickViewIds(R.id.tv_alarm_btn_1);
        addChildClickViewIds(R.id.tv_alarm_btn_2);
    }

    @Override
    protected void convert(BaseViewHolder holder, AlarmListBean.RowsBean bean) {
        holder.setText(R.id.tv_alarm_content_1, bean.getOccurTime());
        holder.setText(R.id.tv_alarm_content_2, bean.getDeviceName());
        holder.setText(R.id.tv_alarm_content_3, bean.getDevLocation());
        holder.setText(R.id.tv_alarm_content_4, bean.getAlarmCause());
        holder.setText(R.id.tv_alarm_content_5, bean.getAlarmCode());
        View view = holder.getView(R.id.color_layout);
        int color;
        if (bean.getAlarmLevel().equals("1")) {
            color = R.drawable.red_conner_bg;
        } else if (bean.getAlarmLevel().equals("2")) {
            color = R.drawable.orange_conner_bg;
        } else {
            color = R.drawable.yellow_conner_bg;
        }
        view.setBackgroundResource(color);
    }
}
