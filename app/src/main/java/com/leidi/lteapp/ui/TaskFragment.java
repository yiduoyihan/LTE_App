package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.util.SpUtilsKey;

/**
 * 我的任务
 *
 * @author yan
 */
public class TaskFragment extends BaseFragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    MainPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fm_task;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        viewPager = view.findViewById(R.id.task_viewpager);
        radioGroup = view.findViewById(R.id.task_rg);
        viewPager.addOnPageChangeListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.setOffscreenPageLimit(1);

        setupViewPager();

        view.findViewById(R.id.tv_task_create_form).setOnClickListener(view1 -> {
                    System.out.println("======head===" + "Bearer " + SPUtils.getInstance().getString(SpUtilsKey.TOKEN));
                    startActivity(new Intent(getActivity(), CreateTaskActivity.class));
                }
        );
    }

    private void setupViewPager() {
        adapter = new MainPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(TaskDoingFragment.getInstance());
        adapter.addFragment(TaskOverFragment.getInstance());
        viewPager.setAdapter(adapter);
    }


    public static TaskFragment getInstance() {
        return new TaskFragment();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.rb_task_bottom1);
                break;
            case 1:
                radioGroup.check(R.id.rb_task_bottom2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_task_bottom1:
                //任务
                viewPager.setCurrentItem(0);
                radioGroup.check(R.id.rb_task_bottom1);
                break;
            case R.id.rb_task_bottom2:
                //设备
                viewPager.setCurrentItem(1);
                radioGroup.check(R.id.rb_task_bottom2);
                break;
            default:
                break;
        }
    }
}
