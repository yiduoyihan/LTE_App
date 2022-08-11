package com.leidi.lteapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.AlarmListAdapter;
import com.leidi.lteapp.adapter.TaskListAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.AlarmListBean;
import com.leidi.lteapp.bean.TaskListBean;
import com.leidi.lteapp.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class AlarmFragment extends BaseFragment {
    RecyclerView recyclerView;
    AlarmListAdapter adapter;
    private List<AlarmListBean.DataBean> beanList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fm_alarm;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        recyclerView = view.findViewById(R.id.rv_alarm_list);
        for (int i = 10; i >= 0; i--) {
            AlarmListBean.DataBean bean = new AlarmListBean.DataBean();
            bean.setTitle("" + i);
            bean.setName("aaaa" + i);
            beanList.add(bean);
        }
        adapter = new AlarmListAdapter(R.layout.item_alarm, beanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showShort("pos" + position);
                if (view.getId() == R.id.tv_alarm_btn_1) {
                    ToastUtils.showShort("诊" + position);
                } else {
                    ToastUtils.showShort("创" + position);
                }
            }
        });

    }

    public static AlarmFragment getInstance() {
        return new AlarmFragment();
    }

}
