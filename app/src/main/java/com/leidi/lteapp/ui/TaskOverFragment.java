package com.leidi.lteapp.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.TaskListAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.TaskListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务已完成页面
 *
 * @author yan
 */
public class TaskOverFragment extends BaseFragment {
    RecyclerView recyclerView;
    TaskListAdapter adapter;
    private List<TaskListBean.DataBean> beanList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fm_task_list;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        recyclerView = view.findViewById(R.id.rv_task_list);
        for (int i = 10; i >= 0; i--) {
            TaskListBean.DataBean bean = new TaskListBean.DataBean();
            bean.setTitle("" + i);
            bean.setName("aaaa" + i);
            beanList.add(bean);
        }
        adapter = new TaskListAdapter(R.layout.item_task, beanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    public static TaskOverFragment getInstance() {
        return new TaskOverFragment();
    }

}
