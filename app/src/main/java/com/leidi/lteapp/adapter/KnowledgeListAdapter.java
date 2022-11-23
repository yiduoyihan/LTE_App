package com.leidi.lteapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.KnowledgeLibBean;

/**
 * @author yan
 * @description 首页列表适配器
 */
public class KnowledgeListAdapter extends BaseQuickAdapter<KnowledgeLibBean.RowsBean, BaseViewHolder> {

    public KnowledgeListAdapter() {
        super(R.layout.item_knowledge);
    }

    @Override
    protected void convert(BaseViewHolder holder, KnowledgeLibBean.RowsBean bean) {
        holder.setText(R.id.tv_knowledge_name, bean.getFileName());
    }
}
