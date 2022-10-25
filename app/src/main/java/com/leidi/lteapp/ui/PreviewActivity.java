package com.leidi.lteapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/10/26 0026.
 * 预览页面
 */

public class PreviewActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private List<ImageView> dotViewsList = new ArrayList<>();
    int flag = 0;
    LinearLayout mLinearLayout;
    private List<String > data;
    ViewPager viewPager;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_preview;
    }

    @Override
    protected void initView() {

        flag = getIntent().getIntExtra("position",0);
        System.out.println("=====pos= "+flag);
        data = (List<String>)getIntent().getSerializableExtra("data");

        setToolbar("预览");

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(10, 10);
        lp.setMargins(5, 5, 5, 5);

        viewPager.setAdapter(new SamplePagerAdapter(this,data.subList(0,data.size())));

        for (int i = 0; i < data.size(); i++) {
            ImageView viewDot = new ImageView(this);
            viewDot.setLayoutParams(lp);
            dotViewsList.add(viewDot);
            mLinearLayout.addView(viewDot);
            if (i == flag) {
                dotViewsList.get(flag).setBackgroundColor(Color.BLACK);
            }else {
                dotViewsList.get(i).setBackgroundColor(Color.GRAY);
            }
        }

        viewPager.setCurrentItem(flag,false);
        viewPager.setOnPageChangeListener(this);
    }

    static class SamplePagerAdapter extends PagerAdapter {
        List<String > picUrl;
        Context context;

        public SamplePagerAdapter(Context context,List<String> data) {
            picUrl = data;
            this.context = context;
        }

        @Override
        public int getCount() {
            return picUrl.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Picasso.get().load(picUrl.get(position)).into(photoView);
            container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dotViewsList.size(); i++) {
            if (i == position) {
//				dotViewsList.get(i).setBackgroundResource(R.mipmap.white_dot);
                dotViewsList.get(i).setBackgroundColor(Color.BLACK);
            } else {
//				dotViewsList.get(i).setBackgroundResource(R.mipmap.dark_dot);
                dotViewsList.get(i).setBackgroundColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
