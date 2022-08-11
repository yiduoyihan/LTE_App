package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseFragment;

/**
 * 我的任务
 *
 * @author yan
 */
public class TaskFragment extends BaseFragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.fm_task;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        viewPager = view.findViewById(R.id.task_viewpager);
        radioGroup = view.findViewById(R.id.task_rg);

        initRadioGroup();
        setupViewPager();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void initRadioGroup() {
        viewPager.setOffscreenPageLimit(1);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
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
        });
    }

    private void setupViewPager() {
        MainPagerAdapter adapter = new MainPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(TaskDoingFragment.getInstance());
        adapter.addFragment(TaskOverFragment.getInstance());
        viewPager.setAdapter(adapter);
    }

    public static TaskFragment getInstance() {
        return new TaskFragment();
    }

}
