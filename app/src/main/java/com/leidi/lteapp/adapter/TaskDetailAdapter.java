package com.leidi.lteapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.widget.GridView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.bean.TaskDetailBean;
import com.leidi.lteapp.ui.PreviewActivity;
import com.leidi.lteapp.ui.VideoPlayActivity;
import com.leidi.lteapp.util.GridViewUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class TaskDetailAdapter extends BaseQuickAdapter<TaskDetailBean.DataBean.AppLteTaskDetailsBean, BaseViewHolder> implements LoadMoreModule {
    private Context context;

    public TaskDetailAdapter(Context context) {
        super(R.layout.task_detail_item);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, TaskDetailBean.DataBean.AppLteTaskDetailsBean bean) {
        holder.setText(R.id.tv_arrive_time, bean.getArriveTime());
        holder.setText(R.id.tv_address, bean.getArrivePosition());
        holder.setText(R.id.tv_complete_person, bean.getUserName());
        holder.setText(R.id.tv_question_description, bean.getFaultDes());
        holder.setText(R.id.tv_gc, bean.getProcessDes());
        holder.setText(R.id.tv_use_tools, bean.getDeviceDes());

        if (null != bean.getLteTaskDetailsPics()) {
            //如果有图片，把图片显示出来
            List<String> pathList = new ArrayList<>();
            for (int i = 0; i < bean.getLteTaskDetailsPics().size(); i++) {
                pathList.add(bean.getLteTaskDetailsPics().get(i).getUrl());
            }
            ShowPicAdapter mAdapter = new ShowPicAdapter(context);
            GridView gridView = holder.getView(R.id.gv_image_choose);
            mAdapter.setData(pathList);
            gridView.setAdapter(mAdapter);
            gridView.setLayoutParams(GridViewUtil.setGridViewHeightBasedOnChildren(gridView, 4));

            gridView.setOnItemClickListener((parent, view, position, id) -> {
                if (pathList.get(position).endsWith(".mp4")) {
                    context.startActivity(new Intent(context, VideoPlayActivity.class)
                            .putExtra("data", pathList.get(position)));
                } else {
                    context.startActivity(new Intent(context, PreviewActivity.class)
                            .putExtra("data", pathList.get(position)));
                }
            });
        }
    }
}
