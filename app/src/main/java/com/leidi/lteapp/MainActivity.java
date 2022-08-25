package com.leidi.lteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.widget.RadioGroup;

import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.event.PicRequest;
import com.leidi.lteapp.ui.AlarmFragment;
import com.leidi.lteapp.ui.DeviceFragment;
import com.leidi.lteapp.ui.SelfFragment;
import com.leidi.lteapp.ui.TaskFragment;
import com.zhihu.matisse.Matisse;

import java.util.List;

import androidx.viewpager.widget.ViewPager;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private PicRequest picRequest;
    MainPagerAdapter adapter;

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
        initData();
    }

    private void initData() {
        picRequest = (SelfFragment) adapter.getItem(3);
    }

    @SuppressLint("NonConstantResourceId")
    private void initRadioGroup() {
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
        adapter.addFragment(DeviceFragment.getInstance());
        adapter.addFragment(AlarmFragment.getInstance());
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
                radioGroup.check(R.id.rb_main_bottom2);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.removeOnPageChangeListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SelfFragment.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {

            List<Uri> uriList = Matisse.obtainResult(data);
            List<String> pathResult = Matisse.obtainPathResult(data);//上传图片时候的路径
            picRequest.getPicPath(pathResult.get(0));
        }
    }

}