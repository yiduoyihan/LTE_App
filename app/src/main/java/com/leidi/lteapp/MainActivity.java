package com.leidi.lteapp;

import static com.leidi.lteapp.util.Constant.SAVE_PATH;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.bean.UpdataBean;
import com.leidi.lteapp.event.ChangeHeadPicEvent;
import com.leidi.lteapp.event.TokenInvalidEvent;
import com.leidi.lteapp.ui.AlarmFragment;
import com.leidi.lteapp.ui.DeviceFragment;
import com.leidi.lteapp.ui.LoginActivity;
import com.leidi.lteapp.ui.SelfFragment;
import com.leidi.lteapp.ui.TaskFragment;
import com.leidi.lteapp.util.DownLoadUtil;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.leidi.lteapp.view.DownloadEnd;
import com.permissionx.guolindev.PermissionX;
import com.rxjava.rxlife.RxLife;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, DownloadEnd {
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
        EventBus.getDefault().register(this);
        return R.layout.activity_main;
    }

    @Override
    protected void onStart() {
        if (SPUtils.getInstance().getString(SpUtilsKey.IP).trim().length() > 0) {
            Url.baseUrl = SPUtils.getInstance().getString(SpUtilsKey.IP).trim();
        }
        super.onStart();
    }

    @Override
    protected void initView() {
//        requestLocationPermission();
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
                    //data不为空，同时服务器版本与本身版本不一致，同时type为1的时候，才强制更新
                    System.out.println("真假：" + bean.getData().getVersion().equals(AppUtils.getAppVersionName()));
                    if (null != bean.getData() && bean.getData().getIssueType().equals("1")
                            && Double.parseDouble(bean.getData().getVersion()) > Double.parseDouble(AppUtils.getAppVersionName())) {
                        newApkUrl = bean.getData().getUrl();
                        showDownloadDialog();
                    }
                }, throwable -> {
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
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
        tvButton.setOnClickListener(v -> DownLoadUtil.sendRequestWithOkHttp(this, newApkUrl, SAVE_PATH, loadingDialog,this));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(this);
        EventBus.getDefault().unregister(this);
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onExitApp(TokenInvalidEvent event) {
        //token过期，退出登录
        SPUtils.getInstance().clear();
        startActivity(new Intent(this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }


    /**
     * 请求定位相关的权限
     */
//    private void requestLocationPermission() {
//        PermissionX.init(this)
//                .permissions(Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        "lte.trunk.permission.READ_PHONE_STATE"
//                )
//                .onExplainRequestReason((scope, deniedList, beforeRequest) -> scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白"))
//                .onForwardToSettings((scope, deniedList) -> scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白"))
//                .request((allGranted, grantedList, deniedList) -> {
//                    if (allGranted) {
//                        startGetSignId();
//                    } else {
//                        ToastUtils.showShort("您拒绝了如下权限：" + deniedList);
//                    }
//                });
//
//    }
//
//    private void startGetSignId() {
//        String str = "111";
//    }

    @Override
    public void downloadResult(String path) {
        AppUtils.installApp(path);
    }
}