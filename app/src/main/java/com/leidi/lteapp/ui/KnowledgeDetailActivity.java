package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.util.WordUtil;

import java.io.File;

/**
 * 查看word的页面
 */
public class KnowledgeDetailActivity extends BaseActivity {

    ProgressBar progressBar;
    WebView mWebView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_detail;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        setToolbar("查看详情");
        String url = getIntent().getStringExtra("url");
        progressBar = findViewById(R.id.progressBar);
        mWebView = findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        //支持js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);//将图片调整到适合webView的大小
        webSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        webSettings.setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true);//设置可以缩放
        webSettings.setDisplayZoomControls(false);//隐藏原生的缩放控件
        //默认为word文件。在新线程中将word保存到本地，然后将word转为html，通过webView查看。
        new Thread(() -> {
            File file = new WordUtil().getNetUrlHttp(url);
            WordUtil wu = new WordUtil(file.getAbsolutePath());
            //webView 的加载必须在和他初始化的线程相同
            runOnUiThread(() -> mWebView.loadUrl("file:///" + wu.htmlPath));
        }).start();
    }

}
