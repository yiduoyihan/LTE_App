package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.leidi.lteapp.R;
import com.leidi.lteapp.adapter.MainPagerAdapter;
import com.leidi.lteapp.base.BaseFragment;
import com.leidi.lteapp.event.TaskSearchEvent;
import com.leidi.lteapp.util.SpUtilsKey;

import org.greenrobot.eventbus.EventBus;

/**
 * 我的任务
 *
 * @author yan
 */
public class TaskFragment extends BaseFragment implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    MainPagerAdapter adapter;
    private RadioButton rb1, rb2, rb3;
    private EditText etSearch;
    private int select_tab = 0;

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
        viewPager.setOffscreenPageLimit(2);

        rb1 = view.findViewById(R.id.rb_task_bottom1);
        rb2 = view.findViewById(R.id.rb_task_bottom2);
        rb3 = view.findViewById(R.id.rb_task_bottom3);

        etSearch = view.findViewById(R.id.et_search_content);
        Button btnSearch = view.findViewById(R.id.btn_start_search);

        getEveryTaskCount();
        setupViewPager();

        view.findViewById(R.id.tv_task_create_form).setOnClickListener(view1 -> {
                    System.out.println("======head===" + "Bearer " + SPUtils.getInstance().getString(SpUtilsKey.TOKEN));
                    startActivity(new Intent(getActivity(), CreateTaskActivity.class));
                }
        );

        btnSearch.setOnClickListener(v -> {
            if (etSearch.getText().toString().trim().length() == 0) {
                ToastUtils.showShort("请输入想要查询的内容");
                //searchValue
            } else {
                //在三个Fragment中都接收，然后判断是在哪个页签之下的内容
                KeyboardUtils.hideSoftInput(v);
                EventBus.getDefault().post(new TaskSearchEvent(select_tab, etSearch.getText().toString().trim()));
            }
        });
    }

    //获取每种任务的数量
    private void getEveryTaskCount() {

//        RxHttp.postForm(Url.)
//                .add("", "")
//                .asClass(BaseBean.class)
//                .observeOn(AndroidSchedulers.mainThread())
//                .to(RxLife.to(this))
//                .subscribe(bean -> {
//                    if (bean.getCode() == 200) {
//                    } else {
//                        ToastUtils.showShort(bean.getMsg());
//                    }
//
//                }, throwable -> {
//                });

//        rb1.setText("未完成(25)");
//        rb2.setText("已结束(39)");
//        rb3.setText("已超时(11)");
    }

    private void setupViewPager() {
        adapter = new MainPagerAdapter(requireActivity().getSupportFragmentManager());
        adapter.addFragment(TaskDoingFragment.getInstance());
        adapter.addFragment(TaskOverFragment.getInstance());
        adapter.addFragment(TaskOverTimeFragment.getInstance());
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
            case 2:
                radioGroup.check(R.id.rb_task_bottom3);
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
                select_tab = 0;
                break;
            case R.id.rb_task_bottom2:
                //设备
                viewPager.setCurrentItem(1);
                radioGroup.check(R.id.rb_task_bottom2);
                select_tab = 1;
                break;
            case R.id.rb_task_bottom3:
                //设备
                viewPager.setCurrentItem(2);
                radioGroup.check(R.id.rb_task_bottom3);
                select_tab = 2;
                break;
            default:
                break;
        }
    }

}
