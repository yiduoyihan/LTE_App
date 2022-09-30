package com.leidi.lteapp.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

public class GridViewUtil {
    public static ViewGroup.LayoutParams setGridViewHeightBasedOnChildren(GridView gridView, int numColumns) {
        // 获取listview的adapter
        if (gridView == null)
            return null;

        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return null;
        }
        // 固定列宽，有多少列
        int col = numColumns;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        // 设置高度
        params.height = totalHeight + (gridView.getVerticalSpacing() * (listAdapter.getCount() / numColumns));
        return params;
    }
}
