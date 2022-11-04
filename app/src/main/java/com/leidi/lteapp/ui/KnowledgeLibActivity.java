package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.KnowledgeListAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.KnowledgeLibBean;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.DownLoadUtil;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.leidi.lteapp.view.DownloadEnd;
import com.leidi.lteapp.wps.FileOpen;
import com.leidi.lteapp.wps.WpsModel;
import com.rxjava.rxlife.RxLife;

import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.util.Objects;

import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.remote.DownloadFileUrlConnectionImpl;
import es.voghdev.pdfviewpager.library.util.FileUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class KnowledgeLibActivity extends BaseActivity implements DownloadEnd {

    EditText etInput;
    Button btnSearch;
    RecyclerView recyclerView;
    KnowledgeListAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowledge_lib;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("知识库");
        controlKeyboard(R.id.page_knowledge);

        String str = getIntent().getStringExtra("一键诊断");
        etInput = findViewById(R.id.et_knowledge_search);
        etInput.setText(str);
        btnSearch = findViewById(R.id.btn_knowledge_search);
        recyclerView = findViewById(R.id.rv_knowledge);
        //数据适配器
        adapter = new KnowledgeListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        requestToSearch(str);
        btnSearch.setOnClickListener(v -> {
            requestToSearch(etInput.getText().toString().trim());
        });
        adapter.setOnItemClickListener((adapter, view, position) -> {
            loadingDialog.show();
            String url = ((KnowledgeLibBean.RowsBean) adapter.getData().get(position)).getFileUrl();
            //开始下载的时候弹出对话框禁止操作并展示下载进度#24
            DownLoadUtil.sendRequestWithOkHttp(this, url, getLocalPath(url), loadingDialog, this);
            //pdf和word 分别去不同的页面查看
//            Class <?> cla = url.endsWith(".pdf") ? RemotePDFActivity.class : KnowledgeDetailActivity.class;
//            startActivity(new Intent(KnowledgeLibActivity.this, cla)
//                    .putExtra("url", url));
        });
    }

    private String getLocalPath(String url) {
        //对本地文件命名，path是http的完整路径，主要得到资源的名字
        String newUrl;
        newUrl = url.split("[?]")[0];
        String[] bb = newUrl.split("/");
        //得到最后一个分隔符后的名字
        String fileName = bb[bb.length - 1];
        //保存到本地的路径Android/data/com.leidi.lteapp/files
        return Constant.SAVE_KNOWLEDGE + fileName;
    }

    /**
     * 请求服务器查询搜索内容
     */
    private void requestToSearch(String searchContent) {
        RxHttp.get(Url.knowledge)
                .add("fileName", searchContent)
                .asResponseList(KnowledgeLibBean.RowsBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    adapter.setList(bean);
                    if (bean.size() == 0) {
                        adapter.setEmptyView(R.layout.empty_view);
                        ToastUtils.showShort("未查询到搜索的内容");
                    }
                }, throwable -> {
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });

    }

    @Override
    public void downloadResult(String path) {
        System.out.println("下载成功：" + path);
        File file = new File(path);
        Bundle bundle = new Bundle();
        bundle.putString(WpsModel.OPEN_MODE, WpsModel.OpenMode.NORMAL); // 打开模式
//        bundle.putBoolean(WpsModel.ENTER_REVISE_MODE, true); // 以修订模式打开文档
//        bundle.putBoolean(WpsModel.SEND_CLOSE_BROAD, true); // 文件关闭时是否发送广播
//        bundle.putBoolean(WpsModel.SEND_SAVE_BROAD, true); // 文件保存时是否发送广播
//        bundle.putBoolean(WpsModel.HOMEKEY_DOWN, true); // 单机home键是否发送广播
//        bundle.putBoolean(WpsModel.BACKKEY_DOWN, true); // 单机back键是否发送广播
//        bundle.putBoolean(WpsModel.SAVE_PATH, true); // 文件这次保存的路径
        bundle.putString(WpsModel.THIRD_PACKAGE, WpsModel.PackageName.NORMAL); // 第三方应用的包名，用于对改应用合法性的验证
        FileOpen.openFile(this, file);
    }
}
