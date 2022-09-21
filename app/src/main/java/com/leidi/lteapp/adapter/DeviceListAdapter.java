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
public class DeviceListAdapter extends BaseQuickAdapter<DeviceListBean.RowsBean, BaseViewHolder> {

    public DeviceListAdapter() {
        super(R.layout.item_device);
    }

    @Override
    protected void convert(BaseViewHolder holder, DeviceListBean.RowsBean bean) {
//       holder.setText(R.id.tv_device_number,bean.getId());
//       holder.setText(R.id.tv_device_d_number,bean.getD_number());
        holder.setText(R.id.tv_device_ip, bean.getDeviceIp());
        holder.setText(R.id.tv_device_address, bean.getDevLocation());
        holder.setText(R.id.tv_device_name, bean.getEquipName());
        if (null == bean.getConnectStatus()) {
            holder.setText(R.id.tv_device_state, "下线");
        } else if (bean.getConnectStatus().equals(1)) {
            holder.setText(R.id.tv_device_state, "上线");
        }
    }
}
