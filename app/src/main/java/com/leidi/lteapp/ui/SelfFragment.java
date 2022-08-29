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

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.event.PicRequest;
import com.leidi.lteapp.util.CommonDialog;
import com.leidi.lteapp.util.GifSizeFilter;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.nanchen.compresshelper.CompressHelper;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;
import com.rxjava.rxlife.RxLife;
import com.squareup.picasso.Picasso;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.List;

import androidx.annotation.Nullable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 我的设置页面
 *
 * @author yan
 */
public class SelfFragment extends BaseFragment implements View.OnClickListener, PicRequest {
    CommonDialog dialog;
    ImageView ivHead;
    //选择图片时候的返回码
    public static final int REQUEST_CODE_CHOOSE = 23;

    @Override
    protected int getLayoutId() {
        return R.layout.fm_self;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        ivHead = view.findViewById(R.id.iv_head_pic);
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
                .onExplainRequestReason(new ExplainReasonCallbackWithBeforeParam() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList, boolean beforeRequest) {
                        scope.showRequestReasonDialog(deniedList, "即将申请的权限是程序必须依赖的权限", "我已明白");
                    }
                })
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "您需要去应用程序设置当中手动开启权限", "我已明白");
                    }
                })
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            openCamera();
                        } else {
                            ToastUtils.showShort("您拒绝了如下权限：" + deniedList);
                        }
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
                .gridExpectedSize(120)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new PicassoEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(isChecked -> {
                    Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                })
                .forResult(REQUEST_CODE_CHOOSE);
    }


    @Override
    public void getPicPath(String path) {
        ToastUtils.showShort(path);
        File newFile = CompressHelper.getDefault(getActivity()).compressToFile(new File(path));
        Picasso.with(getActivity()).load(newFile).into(ivHead);
//        "https://pics5.baidu.com/feed/9213b07eca8065385ef004223f7a784ead34821f.jpeg?token=fe6148e7ff6a6ae120584af1268d5924"
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
                .addHeader("Authorization", "Bearer " + SPUtils.getInstance().getString(SpUtilsKey.TOKEN))
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    if (bean.getCode() == 200) {
                        dialog.dismiss();
                        SPUtils.getInstance().clear();
                        startActivity(new Intent(getActivity(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_NEW_TASK));
                        requireActivity().finish();
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }

                }, throwable -> {
                });

    }

}
