package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.DeviceListAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.DeviceListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class DeviceFragment extends BaseFragment {

    private RadioGroup radioGroup;
    RecyclerView recyclerView;
    DeviceListAdapter adapter;
    List<DeviceListBean.DataBean> dataList = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.fm_device;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        radioGroup = view.findViewById(R.id.device_rg);
        initRadioGroup();
        initData();
        adapter = new DeviceListAdapter(R.layout.item_device,dataList);
        recyclerView = view.findViewById(R.id.rv_device_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            DeviceListBean.DataBean bean = new DeviceListBean.DataBean();
            bean.setNumber(""+i);
            bean.setD_number("d"+i);
            bean.setIp("192.168.200."+i);
            bean.setName("设备"+i);
            bean.setAddress("西安"+i);
            bean.setState(i%2 ==0 ?"正常":"异常");
            dataList.add(bean);
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void initRadioGroup() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_device_bottom1:
                    requestData(1);
                    radioGroup.check(R.id.rb_device_bottom1);
                    break;
                case R.id.rb_device_bottom2:
                    requestData(2);
                    radioGroup.check(R.id.rb_device_bottom2);
                    break;
                case R.id.rb_device_bottom3:
                    requestData(3);
                    radioGroup.check(R.id.rb_device_bottom3);
                    break;
                case R.id.rb_device_bottom4:
                    requestData(4);
                    radioGroup.check(R.id.rb_device_bottom4);
                    break;
                default:
                    break;
            }
        });
    }

    /**
     * 请求列表显示的数据
     */
    private void requestData(int type) {
        //TODO  请求服务器数据



    }

    public static DeviceFragment getInstance() {
        return new DeviceFragment();
    }

}
