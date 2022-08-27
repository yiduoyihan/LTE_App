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

        holder.getView(R.id.tv_flag_pic).setBackgroundResource(bean.getTaskStatus().equals("0") ? R.mipmap.doing_pic : R.mipmap.over_pic);
//        holder.setText(R.id.tv_home_detail, Html.fromHtml(bean.getNoticeContent()));
//        holder.setText(R.id.tv_date,bean.getCreateTime());
//        holder.addOnClickListener(R.id.home_item_delete);
    }
}
