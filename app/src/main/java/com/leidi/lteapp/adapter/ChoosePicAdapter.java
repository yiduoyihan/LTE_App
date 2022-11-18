package com.leidi.lteapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leidi.lteapp.R;
import com.nanchen.compresshelper.CompressHelper;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.List;

/**
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
        holder.itemImage = view.findViewById(R.id.item_grid_image);
        if ("占位图".equals(mPaths.get(position).trim())) {
            Picasso.get().load(R.mipmap.act_send_detail_add_last).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(holder.itemImage);
        } else {
//            File newFile = CompressHelper.getDefault(context).compressToFile(new File(mPaths.get(position).trim()));
            //防止有些图加载不出来显示白块[用上面的代码压缩之后再用框架加载file会有几率导致第一张图显示白块]
            if (mPaths.get(position).trim().endsWith(".mp4")) {
                Glide.with(context).load(mPaths.get(position)).into(holder.itemImage);
            } else {
                Picasso.get()
                        .load("file://" + mPaths.get(position))
                        .fit()
                        .centerCrop()
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .config(Bitmap.Config.RGB_565)
                        .into(holder.itemImage);

            }
        }
        return view;
    }

    public static class ViewHolder {
        ImageView itemImage;
    }

}
