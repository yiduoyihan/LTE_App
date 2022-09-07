package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.util.WordUtil;

import java.io.File;

public class KnowledgeDetailActivity extends BaseActivity {

    ProgressBar progressBar;
    WebView mWebView;
    Handler handler = new Handler();
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
//        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);//将图片调整到适合webView的大小
        webSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        webSettings.setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true);//设置可以缩放
        webSettings.setDisplayZoomControls(false);//隐藏原生的缩放控件

        if (url.contains(".pdf")) {
            // 如果是PDF ，则通过assets里面的js页面来配合显示pdf内容。
            mWebView.setWebViewClient(new WebViewClient() {
                //覆写shouldOverrideUrlLoading实现内部显示网页
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl("file:///android_asset/index.html?" + url);
                    return true;
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    loadingDialog.show();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    handler.postDelayed(() -> loadingDialog.loadSuccess(), 1000);//3秒后执行Runnable中的run方法
                }
            });
            mWebView.loadUrl("file:///android_asset/index.html?" + url);
        } else {
            //默认为word文件。在新线程中将word保存到本地，然后将word转为html，通过webView查看。
            new Thread(() -> {
                File file = new WordUtil().getNetUrlHttp(url);
                WordUtil wu = new WordUtil(file.getAbsolutePath());
                //webView 的加载必须在和他初始化的线程相同
                runOnUiThread(() -> mWebView.loadUrl("file:///" + wu.htmlPath));
            }).start();
        }
    }

}
