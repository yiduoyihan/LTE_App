package com.leidi.lteapp.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * 查看pdf的页面
 * */
public class RemotePDFActivity extends BaseActivity implements DownloadFile.Listener {
    LinearLayout root;
    RemotePDFViewPager remotePDFViewPager;
    PDFPagerAdapter adapter;
    MagicIndicator magicIndicator;
    LinearLayout myLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_remote_pdf;
    }

    @Override
    protected void initView() {
        setToolbar("预览PDF");
        root = findViewById(R.id.remote_pdf_root);
        myLayout = findViewById(R.id.layout_indicatot);
        setDownloadButtonListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (adapter != null) {
            adapter.close();
        }
    }

    protected void setDownloadButtonListener() {
        final Context ctx = this;
        final DownloadFile.Listener listener = this;

        remotePDFViewPager = new RemotePDFViewPager(ctx, getUrlFromEditText(), listener);
        remotePDFViewPager.setId(R.id.pdfViewPager);
    }

    protected String getUrlFromEditText() {
        return getIntent().getStringExtra("url");
    }

    public void updateLayout() {
        root.removeAllViewsInLayout();
        root.addView(toolbar, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.addView(myLayout, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        root.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        initMagicIndicator1(adapter.getCount());
        updateLayout();
    }

    private void initMagicIndicator1(int count) {
        magicIndicator = findViewById(R.id.magic_indicator1);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return count;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(String.valueOf(index + 1));
                simplePagerTitleView.setNormalColor(Color.parseColor("#55555555"));
                simplePagerTitleView.setSelectedColor(Color.RED);
                simplePagerTitleView.setOnClickListener(v -> remotePDFViewPager.setCurrentItem(index));
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#986965"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 20));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.round_indicator_bg));
        ViewPagerHelper.bind(magicIndicator, remotePDFViewPager);

    }

    @Override
    public void onFailure(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}