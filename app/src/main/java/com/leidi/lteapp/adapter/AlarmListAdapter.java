package com.leidi.lteapp.adapter;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.AlarmListBean;
import com.leidi.lteapp.bean.TaskListBean;

import java.util.List;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class AlarmListAdapter extends BaseQuickAdapter<AlarmListBean.DataBean, BaseViewHolder> {

    public AlarmListAdapter(@LayoutRes int layoutResId, @Nullable List<AlarmListBean.DataBean> data) {
        super(layoutResId, data);
        addChildClickViewIds(R.id.tv_alarm_btn_1);
        addChildClickViewIds(R.id.tv_alarm_btn_2);
    }

    @Override
    protected void convert(BaseViewHolder holder, AlarmListBean.DataBean bean) {
        holder.setText(R.id.tv_alarm_content_1, bean.getTitle());
        holder.setText(R.id.tv_alarm_content_2, bean.getName());
        holder.setText(R.id.tv_alarm_content_3, bean.getTitle());
        holder.setText(R.id.tv_alarm_content_4, bean.getName());
        View view = holder.getView(R.id.color_layout);
        int color ;
        if (bean.getColor() == 0){
            color = R.drawable.red_conner_bg;
        }else if (bean.getColor() == 1){
            color = R.drawable.orange_conner_bg;
        }else {
            color = R.drawable.yellow_conner_bg;
        }
        view.setBackgroundResource(color);
    }
}
