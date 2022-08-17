package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.util.CommonDialog;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;

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

    @Override
    protected int getLayoutId() {
        return R.layout.fm_self;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        view.findViewById(R.id.fm_self_item1).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item2).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item3).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item4).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item5).setOnClickListener(this);
        view.findViewById(R.id.fm_self_item6).setOnClickListener(this);
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
            default:
                break;
        }
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
