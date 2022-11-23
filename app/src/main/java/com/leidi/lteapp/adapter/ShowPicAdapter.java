package com.leidi.lteapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.leidi.lteapp.R;

import java.util.List;

/**
 * @author Administrator
 * @date 2017/6/6 0006
 */
public class ShowPicAdapter extends BaseAdapter {
    protected Context context;
    private List<String> mPaths;


    public ShowPicAdapter(Context cx) {
        context = cx;
    }

    public void setData(List<String> paths) {
        mPaths = paths;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mPaths.size();
    }

    @Override
    public Object getItem(int i) {
        return mPaths.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_choose_pic_layout, null);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.itemImage = view.findViewById(R.id.item_grid_image);
        holder.ivPlayBtn = view.findViewById(R.id.iv_play_btn);
        if (mPaths.get(position).endsWith(".mp4")) {
            holder.ivPlayBtn.setVisibility(View.VISIBLE);
        } else {
            holder.ivPlayBtn.setVisibility(View.GONE);
        }
        Glide.with(context).load(mPaths.get(position)).into(holder.itemImage);
        return view;
    }

    public static class ViewHolder {
        ImageView itemImage;
        ImageView ivPlayBtn;
    }

}
