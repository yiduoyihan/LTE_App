package com.leidi.lteapp.adapter;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.TaskListBean;
import com.leidi.lteapp.util.SpUtilsKey;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class TaskListAdapter extends BaseQuickAdapter<TaskListBean.DataBean, BaseViewHolder> implements LoadMoreModule {

    String[] strType = {"任务类型", "应急", "故障", "打卡", "巡检", "演练"};

    public TaskListAdapter() {
        super(R.layout.item_task);
        addChildClickViewIds(R.id.iv_delete_item);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void convert(BaseViewHolder holder, TaskListBean.DataBean bean) {
//        1应急 2故障 3打卡 4巡检 5演练
        holder.setText(R.id.tv_task_content_1, bean.getCreateTime());
        holder.setText(R.id.tv_task_content_2, bean.getBzName());
        holder.setText(R.id.tv_task_content_3, bean.getCreateBy());
        holder.setText(R.id.tv_task_content_4, bean.getDeptName());
        holder.setText(R.id.tv_task_content_5, bean.getDwName());
        holder.setText(R.id.tv_task_content_6, bean.getTaskName());
        holder.setText(R.id.tv_task_content_7, bean.getTaskNo());
        if (null != bean.getTaskType()) {
            holder.setText(R.id.tv_task_content_8, strType[Integer.parseInt(bean.getTaskType())]);
        }
        //taskStatus 0 未完成  1 已完成
        if (bean.getTaskStatus().equals("1")) {
            holder.getView(R.id.iv_delete_item).setVisibility(View.GONE);
            //未完成的话，判断是否是自己创建的任务，只有自己创建的任务才能删除
        }else if (bean.getCreateBy().equals(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME))){
            holder.getView(R.id.iv_delete_item).setVisibility(View.VISIBLE);
        }else {
            holder.getView(R.id.iv_delete_item).setVisibility(View.GONE);
        }
        ImageView imageView = holder.getView(R.id.tv_flag_pic);
        imageView.setBackgroundResource(bean.getTaskStatus().equals("0") ? R.mipmap.doing_pic : R.mipmap.over_pic);
        if (null !=bean.getTaskType() && Integer.parseInt(bean.getTaskType()) < 3) {
            //限时任务
//            timeout taskstatus 0(未完成、未超时) 1（已完成、已超时）
            if (bean.getTaskStatus().equals("0") && bean.getTimeout().equals("0")) {
                //未完成 未超时
                imageView.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.color_gray));
            } else if (bean.getTaskStatus().equals("0") && bean.getTimeout().equals("1")) {
                //未完成 已超时
                imageView.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.color_red));
            } else if (bean.getTaskStatus().equals("1") && bean.getTimeout().equals("0")) {
                //已完成 未超时
                imageView.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.color_green));
            } else if (bean.getTaskStatus().equals("1") && bean.getTimeout().equals("1")) {
                //已完成 已超时
                imageView.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.color_orange));
            }
        }else {
            //非限时任务，区分是否完成就行
            if (bean.getTaskStatus().equals("0")){
                imageView.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.color_blue));
            }else{
                imageView.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), R.color.color_green));
            }
        }
    }
}
