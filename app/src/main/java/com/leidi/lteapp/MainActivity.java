package com.leidi.lteapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.ui.AlarmFragment;
import com.leidi.lteapp.ui.DeviceFragment;
import com.leidi.lteapp.ui.SelfFragment;
import com.leidi.lteapp.ui.TaskFragment;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRadioGroup();
        setupViewPager();
        System.out.println("======"+ Environment.getExternalStorageDirectory ());
    }


    private void initView() {
        viewPager = findViewById(R.id.vp_main);
        radioGroup = findViewById(R.id.rg_main_bottom);
        viewPager.addOnPageChangeListener(this);
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
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
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
}