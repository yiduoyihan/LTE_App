package com.leidi.lteapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.leidi.lteapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/6/6 0006
 */
public class ChoosePicAdapter extends BaseAdapter {
    protected Context context;
    private List<String> mPaths;


    public ChoosePicAdapter(Context cx) {
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
        holder.itemImage = (ImageView) view.findViewById(R.id.item_grid_image);
        if ("占位图".equals(mPaths.get(position))) {
            Picasso.with(context).load(R.mipmap.act_send_detail_add_last).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.itemImage);
        } else {
            Picasso.with(context).load("file://" + mPaths.get(position)).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.itemImage);
        }
        return view;
    }

    public class ViewHolder {
        ImageView itemImage;
    }

}
