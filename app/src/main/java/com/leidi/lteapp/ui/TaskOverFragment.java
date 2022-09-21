package com.leidi.lteapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.TaskListAdapter;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.TaskListBean;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.PageInfoUtil;
import com.leidi.lteapp.util.Url;
import com.leidi.lteapp.view.CustomLoadMoreView;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 任务已完成页面
 *
 * @author yan
 */
public class TaskOverFragment extends BaseFragment {
    RecyclerView recyclerView;
    TaskListAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    private static final int PAGE_SIZE = 10;
    private final PageInfoUtil pageInfoUtil = new PageInfoUtil();

    @Override
    protected int getLayoutId() {
        return R.layout.fm_task_list;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        EventBus.getDefault().register(this);
        //下拉刷新控件
        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(15, 80, 153));
        swipeRefreshLayout.setOnRefreshListener(this::refresh);

        //数据适配器
        adapter = new TaskListAdapter();
        adapter.setAnimationEnable(true);
        adapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        adapter.getLoadMoreModule().setAutoLoadMore(true);
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        adapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
        //列表
        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        // 进入页面，刷新数据
        swipeRefreshLayout.setRefreshing(true);
        refresh();

        initItemClick();
        initItemChildClick();
        initLoadMore();

    }

    private void initLoadMore() {
        //滑动到底部加载更多
        adapter.getLoadMoreModule().setOnLoadMoreListener(this::loadMore);
    }


    /**
     * 刷新
     */
    private void refresh() {
        // 这里的作用是防止下拉刷新的时候还可以上拉加载
        adapter.getLoadMoreModule().setEnableLoadMore(false);
        // 下拉刷新，需要重置页数
        pageInfoUtil.reset();
        requestTaskList();
    }

    /**
     * 加载更多
     */
    private void loadMore() {
        requestTaskList();
    }

    private void initItemChildClick() {
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.iv_delete_item) {
                int deleteId = ((TaskListBean.DataBean) adapter.getData().get(position)).getTaskId();
                toDeleteTask(deleteId, position);
            }
        });
    }

    private void initItemClick() {
        adapter.setOnItemClickListener((adapter1, view1, position) ->
                startActivity(new Intent(getActivity(), TaskDetailActivity.class)
                        .putExtra("taskId", ((TaskListBean.DataBean) adapter.getData().get(position)).getTaskId())
                        .putExtra("type", 2)));
    }

    /**
     * 请求故障单列表
     */
    private void requestTaskList() {
        //taskStatus 0 进行中，1已完成
        RxHttp.get(Url.task_list)
                .add("pageNum", pageInfoUtil.page)
                .add("pageSize", PAGE_SIZE)
                .add("taskStatus", 1)
                .asResponseList(TaskListBean.DataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //停掉刷新的圈圈
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.getLoadMoreModule().setEnableLoadMore(true);

                    if (pageInfoUtil.isFirstPage()) {
                        //如果是加载的第一页数据，用 setData()
                        adapter.setList(bean);
                    } else {
                        //不是第一页，则用add
                        adapter.addData(bean);
                    }

                    if (bean.size() < PAGE_SIZE) {
                        //如果不够一页,显示没有更多数据布局
                        adapter.getLoadMoreModule().loadMoreEnd();
                    } else {
                        adapter.getLoadMoreModule().loadMoreComplete();
                    }
                    //如果数据为0，展示空布局
                    if (bean.size() == 0) {
                        adapter.setEmptyView(R.layout.empty_view);
                    }
                    // page加一
                    pageInfoUtil.nextPage();
                }, throwable -> {
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });

    }


    /**
     * 删除任务
     */
    private void toDeleteTask(int taskId, int position) {
        RxHttp.deleteForm(Url.task_delete + taskId)
                .asClass(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    ToastUtils.showShort(bean);
                    adapter.removeAt(position);
                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));
    }


    public static TaskOverFragment getInstance() {
        return new TaskOverFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onListShouldRefresh(RefreshTaskDoingEvent event) {
        refresh();
    }
}
