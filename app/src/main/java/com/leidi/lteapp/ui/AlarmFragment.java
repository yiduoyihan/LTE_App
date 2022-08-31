package com.leidi.lteapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.AlarmListAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.AlarmListBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.Url;
import com.leidi.lteapp.view.CustomLoadMoreView;
import com.rxjava.rxlife.RxLife;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class AlarmFragment extends BaseFragment {

    RecyclerView recyclerView;
    AlarmListAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fm_alarm;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        //下拉刷新控件
        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.rgb(15, 80, 153));
        swipeRefreshLayout.setOnRefreshListener(this::refresh);
        //数据适配器
        adapter = new AlarmListAdapter();
        adapter.setAnimationEnable(true);
        adapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        adapter.getLoadMoreModule().setAutoLoadMore(true);
        //列表
        recyclerView = view.findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        // 进入页面，刷新数据
        swipeRefreshLayout.setRefreshing(true);
        refresh();

        initItemClick();
        initItemChildClick();
    }


    private void initItemClick() {
        //item 本身的点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                startActivity(new Intent(getActivity(), AlarmDetailActivity.class));
            }
        });
    }

    private void initItemChildClick() {
        //item 后面的按钮点击事件
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                ToastUtils.showShort("pos" + position);
                if (view.getId() == R.id.tv_alarm_btn_1) {
                    startActivity(new Intent(getActivity(), AnalyzeResultActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), CreateTaskActivity.class));
                }
            }
        });
    }

    /**
     * 刷新
     */
    private void refresh() {
        request();
    }

    /**
     * 获取告警数据
     */
    private void request() {
        RxHttp.get(Url.alarm_list)
                .asClass(AlarmListBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //停掉刷新的圈圈
                    swipeRefreshLayout.setRefreshing(false);
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        adapter.setList(bean.getRows());
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> swipeRefreshLayout.setRefreshing(false));


    }

    public static AlarmFragment getInstance() {
        return new AlarmFragment();
    }

}
