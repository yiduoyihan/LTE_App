package com.leidi.lteapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.DeviceListBean;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class DeviceListAdapter extends BaseQuickAdapter<DeviceListBean.RowsBean, BaseViewHolder> {

    public DeviceListAdapter() {
        super(R.layout.item_device);
    }

    @Override
    protected void convert(BaseViewHolder holder, DeviceListBean.RowsBean bean) {
        holder.setText(R.id.tv_device_ip, bean.getDeviceIp());
        holder.setText(R.id.tv_device_address, bean.getDevLocation());
        holder.setText(R.id.tv_device_name, bean.getEquipName());
        if (null == bean.getConnectStatus()) {
            holder.setImageResource(R.id.iv_device_state,R.drawable.red_circle_bg);
        } else if (bean.getConnectStatus().equals("1")) {
            holder.setImageResource(R.id.iv_device_state,R.drawable.green_circle_bg);
        } else if (bean.getConnectStatus().equals("0")) {
            holder.setImageResource(R.id.iv_device_state,R.drawable.red_circle_bg);
        }


    }
}
