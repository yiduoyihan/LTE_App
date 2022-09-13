package com.leidi.lteapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.UpdataBean;
import com.leidi.lteapp.event.ChangeHeadPicEvent;
import com.leidi.lteapp.ui.AlarmFragment;
import com.leidi.lteapp.ui.DeviceFragment;
import com.leidi.lteapp.ui.SelfFragment;
import com.leidi.lteapp.ui.TaskFragment;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;
import com.zhihu.matisse.Matisse;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    MainPagerAdapter adapter;
    //下载中的进度条
    ProgressBar progressBar;
    //下载中的提示文字
    TextView tvDwonloadNum;
    //显示下载中的dialog
    Dialog dialog;
    //新版本的地址
    String newApkUrl;
    //开始下载的按钮
    TextView tvButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initView() {
        stateBarTransparent();
        viewPager = findViewById(R.id.vp_main);
        radioGroup = findViewById(R.id.rg_main_bottom);
        viewPager.addOnPageChangeListener(this);

        initRadioGroup();
        setupViewPager();
        checkUpdate();
    }

    @SuppressLint("NonConstantResourceId")
    private void initRadioGroup() {
        //由于不同的人进来的是不同的页面，因此在首页里面进行了相应的判断，即特殊专业（值为1）才有底部的四个tab，其他的只有2个
        if (!SPUtils.getInstance().getString(SpUtilsKey.IS_TSZY).equals("1")) {
            findViewById(R.id.rb_main_bottom2).setVisibility(View.GONE);
            findViewById(R.id.rb_main_bottom3).setVisibility(View.GONE);
        }
        viewPager.setOffscreenPageLimit(3);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_main_bottom1:
                    //任务
                    viewPager.setCurrentItem(0);
                    radioGroup.check(R.id.rb_main_bottom1);
                    break;
                case R.id.rb_main_bottom2:
                    //设备
                    viewPager.setCurrentItem(1);
                    radioGroup.check(R.id.rb_main_bottom2);
                    break;
                case R.id.rb_main_bottom3:
                    //告警
                    viewPager.setCurrentItem(2);
                    radioGroup.check(R.id.rb_main_bottom3);
                    break;
                case R.id.rb_main_bottom4:
                    //我的
                    viewPager.setCurrentItem(3);
                    radioGroup.check(R.id.rb_main_bottom4);
                    break;
                default:
                    break;
            }
        });
    }

    private void setupViewPager() {
        adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(TaskFragment.getInstance());
        if (SPUtils.getInstance().getString(SpUtilsKey.IS_TSZY).equals("1")) {
            adapter.addFragment(DeviceFragment.getInstance());
            adapter.addFragment(AlarmFragment.getInstance());
        }
        adapter.addFragment(SelfFragment.getInstance());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.rb_main_bottom1);
                break;
            case 1:
                if (SPUtils.getInstance().getString(SpUtilsKey.IS_TSZY).equals("1")) {
                    radioGroup.check(R.id.rb_main_bottom2);
                } else {
                    radioGroup.check(R.id.rb_main_bottom4);
                }
                break;
            case 2:
                radioGroup.check(R.id.rb_main_bottom3);
                break;
            case 3:
                radioGroup.check(R.id.rb_main_bottom4);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * 检查是否有更新
     */
    private void checkUpdate() {
        RxHttp.get(Url.check_update)
                .asClass(UpdataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        if (!bean.getData().getVersion().equals(String.valueOf(AppUtils.getAppVersionCode()))) {
                            //1为强制更新 版本号一致，无更新
                            if (bean.getData().getIssueType().equals("1")) {
                                newApkUrl = bean.getData().getUrl();
                                showDownloadDialog();
                            }
                        }
                    }
                }, throwable -> {
                });

    }

    private void showDownloadDialog() {
        @SuppressLint("InflateParams")
        View dialogView = LayoutInflater.from(this).inflate(R.layout.download_dialog, null);//解析我们自己写的布局
        dialog = new AlertDialog.Builder(this).create();//创建一个dialog
        dialog.show();//此处dialog应该先show然后再加载布局，否则会报错
        dialog.setContentView(dialogView);
        //初始化控件
        progressBar = dialogView.findViewById(R.id.progressBar);
        tvDwonloadNum = dialogView.findViewById(R.id.tv_download_num);
        tvButton = dialogView.findViewById(R.id.tv_start_download);
        tvButton.setVisibility(View.VISIBLE);
        tvButton.setOnClickListener(v -> downLoadNewApk());
        dialog.setCanceledOnTouchOutside(false);
    }

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void downLoadNewApk() {
        tvButton.setVisibility(View.GONE);
        //开始下载的时候弹出对话框禁止操作并展示下载进度#24
        String destPath = Environment.getExternalStorageDirectory() + "/Download/html/lte_gz.apk";
//        RxHttp.get("http://t.xiazaicc.com/001/3177")
        RxHttp.get(newApkUrl)
                .asAppendDownload(destPath, AndroidSchedulers.mainThread(), progress -> {
                    //下载进度回调,0-100，仅在进度有更新时才会回调
                    int currentProgress = progress.getProgress(); //当前进度 0-100
                    progressBar.setProgress(currentProgress);
                    tvDwonloadNum.setText("正在下载" + currentProgress + "%,请耐心等待");
                    if (currentProgress == 100) {
                        dialog.dismiss();
                    }
                }) //指定主线程回调
                .subscribe(s -> { //s为String类型
                    //下载成功，处理相关逻辑
                    //打开apk并提示安装
                    AppUtils.installApp(destPath);
                }, throwable -> {
                    //下载失败，处理相关逻辑
                    dialog.dismiss();
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(this);
    }

    /**
     * 这里用来接收selfFragment中换图像时候选择头像的结果信息，然后再将结果信息发送回到SelfFragment中去加载图片，显示在imageview中
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelfFragment.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            EventBus.getDefault().post(new ChangeHeadPicEvent(Matisse.obtainPathResult(data).get(0)));
        }
    }

}