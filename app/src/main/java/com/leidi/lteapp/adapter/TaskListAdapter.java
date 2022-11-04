package com.leidi.lteapp.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.TaskListBean;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class TaskListAdapter extends BaseQuickAdapter<TaskListBean.DataBean, BaseViewHolder> implements LoadMoreModule {

    public TaskListAdapter() {
        super(R.layout.item_task);
        addChildClickViewIds(R.id.iv_delete_item);
    }

    @Override
    protected void convert(BaseViewHolder holder, TaskListBean.DataBean bean) {
        holder.setText(R.id.tv_task_content_1, bean.getCreateTime());
        holder.setText(R.id.tv_task_content_2, bean.getBzName());
        holder.setText(R.id.tv_task_content_3, bean.getCreateBy());
        holder.setText(R.id.tv_task_content_4, bean.getDeptName());
        holder.setText(R.id.tv_task_content_5, bean.getDwName());
        holder.setText(R.id.tv_task_content_6, bean.getTaskName());
        holder.setText(R.id.tv_task_content_7, "待补充");
        holder.setText(R.id.tv_task_content_8, "待补充");

        holder.getView(R.id.tv_flag_pic).setBackgroundResource(bean.getTaskStatus().equals("0") ? R.mipmap.doing_pic : R.mipmap.over_pic);
        if (!bean.getTaskStatus().equals("1")){
            holder.getView(R.id.iv_delete_item).setVisibility(View.VISIBLE);
        }
    }
}
