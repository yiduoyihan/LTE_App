package com.leidi.lteapp.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.TaskListBean;

import java.util.List;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class TaskListAdapter extends BaseQuickAdapter<TaskListBean.DataBean, BaseViewHolder> {

    public TaskListAdapter(@LayoutRes int layoutResId, @Nullable List<TaskListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, TaskListBean.DataBean bean) {
        holder.setText(R.id.tv_task_content_1, bean.getTitle());
        holder.setText(R.id.tv_task_content_3, bean.getName());
//        holder.setText(R.id.tv_home_detail, Html.fromHtml(bean.getNoticeContent()));
//        holder.setText(R.id.tv_date,bean.getCreateTime());
//        holder.addOnClickListener(R.id.home_item_delete);
    }
}
