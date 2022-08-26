package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.event.TaskRequest;
import com.leidi.lteapp.util.Constant;

/**
 * 我的任务
 *
 * @author yan
 */
public class TaskFragment extends BaseFragment {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    TaskRequest taskRequest;
    MainPagerAdapter adapter;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int resultCode = result.getResultCode();
            if (resultCode == Constant.SUCCESS_CODE){
                taskRequest.refreshTaskList();
            }
        }
    });

    @Override
    protected int getLayoutId() {
        return R.layout.fm_task;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState, View view) {
        viewPager = view.findViewById(R.id.task_viewpager);
        radioGroup = view.findViewById(R.id.task_rg);
        view.findViewById(R.id.tv_task_create_form).setOnClickListener(view1 ->
                launcher.launch(new Intent(getActivity(), CreateTaskActivity.class))
        );

        initRadioGroup();
        setupViewPager();
        initTaskRequest();

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

    private void initTaskRequest() {
        taskRequest = (TaskDoingFragment) adapter.getItem(0);
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
        adapter = new MainPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(TaskDoingFragment.getInstance());
        adapter.addFragment(TaskOverFragment.getInstance());
        viewPager.setAdapter(adapter);
    }

    public static TaskFragment getInstance() {
        return new TaskFragment();
    }

}
