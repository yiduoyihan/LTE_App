package com.leidi.lteapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseFragment;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class DeviceFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fm_device;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
//        setToolbar(View.GONE, "我的", View.GONE);
    }

    public static DeviceFragment getInstance() {
        return new DeviceFragment();
    }

}
