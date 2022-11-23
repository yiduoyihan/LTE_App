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
import com.leidi.lteapp.adapter.AlarmListAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.bean.AlarmListBean;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.leidi.lteapp.view.CustomLoadMoreView;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;

import java.util.Objects;

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
    String strTaskName;

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

        adapter.setOnItemClickListener((adapter, view, position) -> {
            EventBus.getDefault().postSticky(adapter.getData().get(position));
            startActivity(new Intent(getActivity(), AlarmDetailActivity.class));
        });
    }

    private void initItemChildClick() {
        //item 后面的按钮点击事件
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.tv_alarm_btn_1) {
                String alarmCause = ((AlarmListBean.RowsBean) adapter.getData().get(position)).getAlarmCode();
                startActivity(new Intent(getActivity(), KnowledgeLibActivity.class)
                        .putExtra("一键诊断", alarmCause));
            } else {
                String deviceName = ((AlarmListBean.RowsBean) adapter.getData().get(position)).getDeviceName();
                String devLocation = ((AlarmListBean.RowsBean) adapter.getData().get(position)).getDevLocation();
                String alarmLv = ((AlarmListBean.RowsBean) adapter.getData().get(position)).getAlarmLevel();
                strTaskName = deviceName + "  " + devLocation + " 级别：" + alarmLv;
                String alarmCause = ((AlarmListBean.RowsBean) adapter.getData().get(position)).getAlarmCause();
                startActivity(new Intent(getActivity(), CreateTaskActivity.class)
                        .putExtra("title", strTaskName)
                        .putExtra("type","alarm")
                        .putExtra("content", alarmCause)
                );
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
                .asResponseList(AlarmListBean.RowsBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                            //停掉刷新的圈圈
                            swipeRefreshLayout.setRefreshing(false);
                            adapter.setList(bean);
                        }, throwable -> {
                            swipeRefreshLayout.setRefreshing(false);
                            ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                        }

                );


    }

    public static AlarmFragment getInstance() {
        return new AlarmFragment();
    }

}
