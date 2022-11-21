package com.leidi.lteapp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.ChoosePicAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.MyApp;
import com.leidi.lteapp.bean.SaveMsgDao;
import com.leidi.lteapp.bean.SaveMsgDaoDao;
import com.leidi.lteapp.bean.TaskDetailBean;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.event.RefreshTaskOverEvent;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.GifSizeFilter;
import com.leidi.lteapp.util.GridViewUtil;
import com.leidi.lteapp.util.SelfEngine;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.nanchen.compresshelper.CompressHelper;
import com.permissionx.guolindev.PermissionX;
import com.rxjava.rxlife.RxLife;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * @author caiwu
 * @description 到达现场页面
 */
public class ArriveSiteActivity extends BaseActivity {

    private String taskNo;
    /**
     * 图片真实路径
     */
    protected List<String> pathList = new ArrayList<>();
    /**
     * 上传服务器时候的文件列表
     */
    List<File> files = new ArrayList<>();
    /**
     * 选择图片之后展示图片的adapter
     */
    private ChoosePicAdapter mAdapter;
    /**
     * 选择图片时候的返回码
     */
    private static final int REQUEST_CODE_CHOOSE = 23;
    /**
     * 上传图片时最多可选数量
     */
    private static final int CAN_CHOOSE_PIC_NUM = 10;
    //保存的数据
    List<SaveMsgDao> datas = new ArrayList<>();
    private EditText et1, et2, et3;
    TextView tvAddress;

    private View progressBar;
    GridView gridView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_arrive_site;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initView() {
        setToolbar("到达现场");
        controlKeyboard(R.id.arrive_site_page);
        initDefaultData();
        initGridView();
        findViewById(R.id.btn_task_over).setOnClickListener(v -> submitTaskProcess());
        taskNo = getIntent().getStringExtra("taskNo");
        greenDaoQuery();
    }

    private void initDefaultData() {
        TextView arriveTime = findViewById(R.id.tv_arrive_time);
        tvAddress = findViewById(R.id.tv_address);
        String address = getIntent().getStringExtra("address");
        tvAddress.setText(address);
        TextView tvTaskPerson = findViewById(R.id.tv_task_person);

        et1 = findViewById(R.id.et_question_description);
        et2 = findViewById(R.id.et_work_description);
        et3 = findViewById(R.id.et_tool_description);
        progressBar = findViewById(R.id.progress_upload);

        if (null == getIntent().getStringExtra("time")) {
            long sysTime = System.currentTimeMillis();
            CharSequence sysTimeStr = DateFormat.format("yyyy-dd-MM HH:mm:ss", sysTime);
            arriveTime.setText(sysTimeStr);
        } else {
            arriveTime.setText(getIntent().getStringExtra("time"));
        }
        tvTaskPerson.setText(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME));

        if (tvAddress.getText().toString().trim().isEmpty()) {
            //如果没有地址的时候获取一下地址[为什么呢，因为服务器在上一个页面传ID过去之后不给你返回地址，只能自己多请求一次咯]
            initData();
        }
    }

    /**
     * 显示位置
     */
    private void initData() {
        int taskId = getIntent().getIntExtra("taskId", 0);
        RxHttp.get(Url.task_detail + taskId)
                .asResponse(TaskDetailBean.DataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
//                    tvAddress.setText(bean.getAppLteTaskDetails().get);
                }, throwable -> {
                    ToastUtils.showShort(ErrorUtils.whichError(
                            Objects.requireNonNull(throwable.getMessage())));
                });
    }

    /**
     * 展示选择的图片的列表
     */
    private void initGridView() {
        pathList.add("占位图");
        gridView = findViewById(R.id.gv_image_choose);
        mAdapter = new ChoosePicAdapter(this);
        mAdapter.setData(pathList);
        gridView.setAdapter(mAdapter);
        //点击gridview的item的事件的时候，先请求camera和存储权限
        gridView.setOnItemClickListener((parent, view, position, id) -> requestPermission());
    }

    /**
     * 请求相机相册的权限
     */
    private void requestPermission() {
        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .onExplainRequestReason((scope, deniedList, beforeRequest) -> scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白"))
                .onForwardToSettings((scope, deniedList) -> scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白"))
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        startChoosePic();
                    } else {
                        ToastUtils.showShort("您拒绝了如下权限：" + deniedList);
                    }
                });
    }

    private void startChoosePic() {
        Matisse.from(this)
                .choose(MimeType.ofAll(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.leidi.lteapp.fileprovider", "test"))
                .maxSelectable(CAN_CHOOSE_PIC_NUM)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(
                        getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new SelfEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(isChecked -> Log.e("isChecked", "onCheck: isChecked=" + isChecked))
                .forResult(REQUEST_CODE_CHOOSE);
    }


    /**
     * 提交任务处理流程
     */
    private void submitTaskProcess() {
        double videoSize = 0;
        //将选中的几个图片的真实路径转为file 添加到list中,从1开始跳过占位的+图片
        for (int i = 0; i < pathList.size() - 1; i++) {
            if (pathList.get(i).endsWith(".mp4")) {
                File video = new File(pathList.get(i));
                files.add(video);
                videoSize = Double.parseDouble(FileUtils.getSize(video).replace("MB", "").trim());
            } else {
                File newFile = CompressHelper.getDefault(this).compressToFile(new File(pathList.get(i).trim()));
                files.add(newFile);
            }
        }

        if (videoSize > 50) {
            ToastUtils.showShort("上传的视频不能超过50MB");
            return;
        }

        if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty()) {
            ToastUtils.showShort("各项描述不能为空");
            return;
        }
        //还需要添加上传中的dialog
        progressBar.setVisibility(View.VISIBLE);
        RxHttp.postForm(Url.task_complete)
                .add("faultDes", et1.getText().toString())
                .add("processDes", et2.getText().toString())
                .add("deviceDes", et3.getText().toString())
                .add("taskNo", taskNo)
                .add("completePosition", "")
                .add("completePositionNo", getIntent().getStringExtra("signId"))
                .add("detailsId", getIntent().getLongExtra("detailsId", 0))
                .addFiles("files", files)
                .asResponse(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    ToastUtils.showShort("任务完成");
                    progressBar.setVisibility(View.GONE);
                    //如果本地数据库有数据，则删除那一条，没有就不处理
                    if (datas.size() > 0) {
                        MyApp.getmDaoSession().getSaveMsgDaoDao().delete(datas.get(0));
                    }
                    //任务完成同时刷新已完成列表和进行中列表
                    EventBus.getDefault().post(new RefreshTaskDoingEvent());
                    EventBus.getDefault().post(new RefreshTaskOverEvent());
                    finish();
                }, throwable -> {
                    progressBar.setVisibility(View.GONE);
                    //如果提交失败，保存提交数据到本地数据库
                    saveData(et1.getText().toString(), et2.getText().toString(),
                            et3.getText().toString(), taskNo, pathList);
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });

    }

    //保存数据到greenDao
    private void saveData(String toString, String toString1, String toString2, String taskNo, List<String> pathList) {
        if (datas.size() > 0) {
            //表示之前有。现在可以更新
            SaveMsgDao saveMsgDao = datas.get(0);
            saveMsgDao.setTaskId(taskNo);
            saveMsgDao.setText1(toString);
            saveMsgDao.setText2(toString1);
            saveMsgDao.setText3(toString2);
            saveMsgDao.setPicUrl(pathList.toString());
            MyApp.getmDaoSession().getSaveMsgDaoDao().update(saveMsgDao);
        } else {
            //之前没有，直接新增
            SaveMsgDao saveMsgDao = new SaveMsgDao();
            saveMsgDao.setTaskId(taskNo);
            saveMsgDao.setText1(toString);
            saveMsgDao.setText2(toString1);
            saveMsgDao.setText3(toString2);
            saveMsgDao.setPicUrl(pathList.toString());
            MyApp.getmDaoSession().getSaveMsgDaoDao().insertOrReplace(saveMsgDao);
        }

    }


    /**
     * 查询数据
     */
    public void greenDaoQuery() {
        datas = MyApp
                .getmDaoSession()
                .getSaveMsgDaoDao()
                .queryBuilder()
                .where(SaveMsgDaoDao.Properties.TaskId.eq(taskNo))
                .list();
        System.out.println("=========data.size= " + datas.size());
        if (datas.size() > 0) {
            KeyboardUtils.hideSoftInput(this);
            et1.setText(datas.get(0).getText1());
            et2.setText(datas.get(0).getText2());
            et3.setText(datas.get(0).getText3());
            pathList.clear();
            pathList.addAll(Arrays.asList(datas.get(0).getPicUrl()
                    .substring(1, datas.get(0).getPicUrl().length() - 1)
                    .split(",")));
            mAdapter.setData(pathList);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            pathList.clear();
            pathList.addAll(Matisse.obtainPathResult(data));
            if (pathList.size() < CAN_CHOOSE_PIC_NUM) {
                pathList.add("占位图");
            }
            System.out.println("======选择之后的图：" + pathList.toString());
            mAdapter.setData(pathList);
            //gridview 嵌套在scrollview中，动态计算gridview高度
            gridView.setLayoutParams(GridViewUtil.setGridViewHeightBasedOnChildren(gridView, 4));
        }
    }
}
