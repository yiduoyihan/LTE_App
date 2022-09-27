package com.leidi.lteapp.ui;


import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 签到页面
 *
 * @author caiwu
 */
public class KnowledgeLibActivity extends BaseActivity {

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
        adapter.setOnItemClickListener((adapter, view, position) -> startActivity(new Intent(KnowledgeLibActivity.this, KnowledgeDetailActivity.class)
                .putExtra("url", ((KnowledgeLibBean.RowsBean) adapter.getData().get(position)).getFileUrl())));
    }

    /**
     * 请求服务器查询搜索内容
     */
    private void requestToSearch(String searchContent) {
        RxHttp.get(Url.knowledge)
                .add("fileName", searchContent)
//                .asClass(KnowledgeLibBean.class)
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
}
