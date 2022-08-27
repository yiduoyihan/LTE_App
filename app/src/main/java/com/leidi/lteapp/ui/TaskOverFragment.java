package com.leidi.lteapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.TaskListAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.TaskListBean;
import com.leidi.lteapp.util.Url;

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
        adapter = new TaskListAdapter(R.layout.item_task, beanList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showShort("pos" + position);
                if (view.getId() == R.id.iv_delete_item) {
                    ToastUtils.showShort("删除" + position);
                }
            }
        });
        adapter.setOnItemClickListener((adapter1, view1, position) ->
                startActivity(new Intent(getActivity(), TaskDetailActivity.class).putExtra("type", 2)));

        requestTaskList();
    }

    /**
     * 请求故障单列表
     */
    private void requestTaskList() {
        //taskStatus 0 进行中，1已完成
        RxHttp.get(Url.task_list)
                .add("pageNum", 1)
                .add("pageSize", 10)
                .add("taskStatus",1)
                .asClass(TaskListBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bean -> {
                    if (bean.getCode() == 200) {
                        beanList.addAll(bean.getData());
                        if (beanList.size()==0) {
                            adapter.setEmptyView(R.layout.empty_view);
                        }else {
                            adapter.setList(beanList);
                        }
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                });

    }


    public static TaskOverFragment getInstance() {
        return new TaskOverFragment();
    }

}
