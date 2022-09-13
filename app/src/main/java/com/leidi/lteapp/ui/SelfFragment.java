package com.leidi.lteapp.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.base.UploadHeadPicBean;
import com.leidi.lteapp.event.ChangeHeadPicEvent;
import com.leidi.lteapp.util.CommonDialog;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.GifSizeFilter;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.nanchen.compresshelper.CompressHelper;
import com.permissionx.guolindev.PermissionX;
import com.rxjava.rxlife.RxLife;
import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class SelfFragment extends BaseFragment implements View.OnClickListener {
    CommonDialog dialog;
    ImageView ivHead;
    /**
     * 选择图片时候的返回码
     */
    public static final int REQUEST_CODE_CHOOSE = 23;

    @Override
    protected int getLayoutId() {
        return R.layout.fm_self;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        EventBus.getDefault().register(this);
        ivHead = view.findViewById(R.id.iv_head_pic);
        if (SPUtils.getInstance().getString(SpUtilsKey.HEAD_PIC).length() > 0) {
            Picasso.with(getActivity()).load(SPUtils.getInstance().getString(SpUtilsKey.HEAD_PIC)).into(ivHead);
        }
        view.findViewById(R.id.fm_self_item1).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item2).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item3).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item4).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item5).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item6).setOnClickListener(this);
        view.findViewById(R.id.iv_open_camera).setOnClickListener(this);
        TextView tvNickName = view.findViewById(R.id.tv_nickname);
        tvNickName.setText(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME));
    }

    public static SelfFragment getInstance() {
        return new SelfFragment();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fm_self_item1:
                startActivity(new Intent(getActivity(), SignActivity.class));
                break;
            case R.id.fm_self_item2:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.fm_self_item3:
                startActivity(new Intent(getActivity(), KnowledgeLibActivity.class));
                break;
            case R.id.fm_self_item4:
                startActivity(new Intent(getActivity(), CheckUpdateActivity.class));
                break;
            case R.id.fm_self_item5:
                startActivity(new Intent(getActivity(), AboutOursActivity.class));
                break;
            case R.id.fm_self_item6:
                showOutLoginDialog();
                break;
            case R.id.iv_open_camera:
                requestPermission();
                break;
            default:
                break;
        }
    }


    private void requestPermission() {
        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .onExplainRequestReason((scope, deniedList, beforeRequest) -> scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白"))
                .onForwardToSettings((scope, deniedList) -> scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白"))
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        openCamera();
                    } else {
                        ToastUtils.showShort("您拒绝了如下权限：" + deniedList);
                    }
                });
    }


    private void openCamera() {
        Matisse.from(getActivity())
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(
                        new CaptureStrategy(true, "com.leidi.lteapp.fileprovider", "test"))
                .maxSelectable(1)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new PicassoEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(isChecked -> Log.e("isChecked", "onCheck: isChecked=" + isChecked))
                .forResult(REQUEST_CODE_CHOOSE);
    }

    private void showOutLoginDialog() {
        dialog = new CommonDialog(getActivity(), R.layout.out_login_dialog);
        if (dialog.isShowing()) {
            return;
        }
        dialog.setTitle("您确定要退出程序吗？")
                .setWight(true)
                .setNegative("取消").setPositive("确定")
                .setOnClickBottomListener(new CommonDialog.OnClickBottomListener() {
                    @Override
                    public void onPositiveClick() {
                        requestLoginOut();
                    }

                    @Override
                    public void onNegativeClick() {
                        dialog.dismiss();
                    }
                })
                .show();
        dialog.setCanceledOnTouchOutside(true);
    }


    private void requestLoginOut() {
        RxHttp.postForm(Url.login_out)
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        dialog.dismiss();
                        SPUtils.getInstance().clear();
                        startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NEW_TASK));
                        requireActivity().finish();
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHeadPicChange(ChangeHeadPicEvent event) {
        //先上传头像到服务器，然后同时本地加载显示头像
        File newFile = CompressHelper.getDefault(getActivity()).compressToFile(new File(event.getHeadPicPath()));
        upLoadPic(newFile);
    }

    private void upLoadPic(File newFile) {
        RxHttp.postForm(Url.upload_pic)
                .addFile("avatarfile", newFile)
                .asClass(UploadHeadPicBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == 200) {
                        ToastUtils.showShort("上传成功");
                        SPUtils.getInstance().put(SpUtilsKey.HEAD_PIC, bean.getImgUrl());
                        Picasso.with(getActivity()).load(newFile).into(ivHead);
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> {
                    ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage())));
                });
    }
}
