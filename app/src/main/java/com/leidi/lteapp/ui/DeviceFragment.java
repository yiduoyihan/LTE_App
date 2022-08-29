package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.DeviceListAdapter;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.DeviceListBean;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class DeviceFragment extends BaseFragment {

    private RadioGroup radioGroup;
    RecyclerView recyclerView;
    DeviceListAdapter adapter;
//    List<DeviceListBean.RowsBean> dataList = new ArrayList<>();
    int requestFlag = 1;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fm_device;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        radioGroup = view.findViewById(R.id.device_rg);
        initRadioGroup();
        requestData(requestFlag);
        adapter = new DeviceListAdapter();
        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        //下拉刷新控件
        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(15, 80, 153));
        swipeRefreshLayout.setOnRefreshListener(this::refresh);
    }

    @SuppressLint("NonConstantResourceId")
    private void initRadioGroup() {
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_device_bottom1:
                    requestFlag = 1;
                    requestData(requestFlag);
                    radioGroup.check(R.id.rb_device_bottom1);
                    break;
                case R.id.rb_device_bottom2:
                    requestFlag = 2;
                    requestData(requestFlag);
                    radioGroup.check(R.id.rb_device_bottom2);
                    break;
                case R.id.rb_device_bottom3:
                    requestFlag = 3;
                    requestData(requestFlag);
                    radioGroup.check(R.id.rb_device_bottom3);
                    break;
                case R.id.rb_device_bottom4:
                    requestFlag = 4;
                    requestData(requestFlag);
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
        RxHttp.get(Url.device_list)
                .add("deviceType", type)
                .asClass(DeviceListBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    swipeRefreshLayout.setRefreshing(false);
                    if (bean.getCode() == 200) {
                        adapter.setList(bean.getRows());
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {
                    swipeRefreshLayout.setRefreshing(false);
                });


    }


    /**
     * 刷新
     */
    private void refresh() {
        requestData(requestFlag);
    }


    public static DeviceFragment getInstance() {
        return new DeviceFragment();
    }

}
