package com.leidi.lteapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.DeviceListBean;
import com.leidi.lteapp.bean.TaskListBean;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class DeviceListAdapter extends BaseQuickAdapter<DeviceListBean.DataBean, BaseViewHolder> {

    public DeviceListAdapter(@LayoutRes int layoutResId, @Nullable List<DeviceListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, DeviceListBean.DataBean bean) {
       holder.setText(R.id.tv_device_number,bean.getNumber());
       holder.setText(R.id.tv_device_d_number,bean.getD_number());
       holder.setText(R.id.tv_device_ip,bean.getIp());
       holder.setText(R.id.tv_device_address,bean.getAddress());
       holder.setText(R.id.tv_device_name,bean.getName());
       holder.setText(R.id.tv_device_state,bean.getState());
    }
}
