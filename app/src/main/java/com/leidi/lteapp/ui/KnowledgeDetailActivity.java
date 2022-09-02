package com.leidi.lteapp.ui;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;

import rxhttp.wrapper.entity.Progress;

public class KnowledgeDetailActivity extends BaseActivity {

    ProgressBar progressBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_detail;
    }

    Handler handler = new Handler();

    @Override
    protected void initView() {
        setToolbar("查看详情");

        String url = getIntent().getStringExtra("url");

        progressBar = findViewById(R.id.progressBar);
        WebView mWebView = findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setAllowFileAccess(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);//提高渲染的优先级设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        webSettings.setSupportZoom(true);//支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true);//设置可以缩放
        webSettings.setDisplayZoomControls(false);//隐藏原生的缩放控件

        mWebView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl("file:///android_asset/index.html?" + url);
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

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.loadSuccess();
                    }
                }, 1000);//3秒后执行Runnable中的run方法
            }
        });
        mWebView.loadUrl("file:///android_asset/index.html?" + url);
    }
}
