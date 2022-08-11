package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.util.ToastUtils;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class SelfFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getLayoutId() {
        return R.layout.fm_self;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        view.findViewById(R.id.fm_self_item1).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item2).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item3).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item4).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item5).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item6).setOnClickListener(this);
    }

    public static SelfFragment getInstance() {
        return new SelfFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fm_self_item1:
                ToastUtils.showShort("1");
                break;
            case R.id.fm_self_item2:
                ToastUtils.showShort("2");
                break;
            case R.id.fm_self_item3:
                ToastUtils.showShort("3");
                break;
            case R.id.fm_self_item4:
                ToastUtils.showShort("4");
                break;
            case R.id.fm_self_item5:
                ToastUtils.showShort("5");
                break;
            case R.id.fm_self_item6:
                ToastUtils.showShort("6");
                break;
            default:
                break;
        }
    }
}
