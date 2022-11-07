package com.leidi.lteapp.ui;

import android.annotation.SuppressLint;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.util.ErrorUtils;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 创建故障单页面
 *
 * @author 阎
 */
public class CreateTaskActivity extends BaseActivity {
    EditText et1, et2;
    TextView tvStartTime, tvEndTime;
    String flag;
    TimePickerView pvTime;
    LinearLayout layoutShowTimeView;
    RadioGroup radioGroup;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_task;
    }

    @Override
    protected void initView() {
        setToolbar("创建故障单");
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);
        TextView tv1 = findViewById(R.id.tv_create_task_start_time);
        TextView tv2 = findViewById(R.id.tv_create_task_person);
        TextView tv3 = findViewById(R.id.tv_create_task_group);
        TextView tv4 = findViewById(R.id.tv_create_task_specialized);
        TextView tv5 = findViewById(R.id.tv_create_task_company);

        tv1.setText(sysTimeStr);
        tv2.setText(SPUtils.getInstance().getString(SpUtilsKey.NICK_NAME));
        tv3.setText(SPUtils.getInstance().getString(SpUtilsKey.USER_BZ));
        tv4.setText(SPUtils.getInstance().getString(SpUtilsKey.USER_ZY));
        tv5.setText(SPUtils.getInstance().getString(SpUtilsKey.USER_DW));

        et1 = findViewById(R.id.et_create_task_name);
        et2 = findViewById(R.id.et_create_task_content);
        findViewById(R.id.btn_create_task).setOnClickListener(view -> createWorkForm());
        controlKeyboard(R.id.rl_create_task);

        et1.setText(getIntent().getStringExtra("title"));
        et2.setText(getIntent().getStringExtra("content"));

        layoutShowTimeView = findViewById(R.id.layout_show_time_view);

        tvStartTime = findViewById(R.id.tv_plan_start_time);
        tvEndTime = findViewById(R.id.tv_plan_end_time);

        initTimePick();
        initChooseTime();
        initRadioGroup();
        //区分是不是从告警页面的创建故障单而来
        if (null != getIntent().getStringExtra("type") && getIntent().getStringExtra("type").equals("alarm")) {
            radioGroup.check(R.id.rbtn_1);
        }
    }

    private void initChooseTime() {
        tvStartTime.setOnClickListener(v -> {
            flag = "start";
            pvTime.show();
        });

        tvEndTime.setOnClickListener(v -> {
            flag = "end";
            pvTime.show();
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void initRadioGroup() {
        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbtn_1:
                    layoutShowTimeView.setVisibility(View.VISIBLE);
                    radioGroup.check(R.id.rbtn_1);
                    break;
                case R.id.rbtn_2:
                    layoutShowTimeView.setVisibility(View.VISIBLE);
                    radioGroup.check(R.id.rbtn_2);
                    break;
                case R.id.rbtn_3:
                    layoutShowTimeView.setVisibility(View.GONE);
                    radioGroup.check(R.id.rbtn_3);
                    break;
                case R.id.rbtn_4:
                    layoutShowTimeView.setVisibility(View.GONE);
                    radioGroup.check(R.id.rbtn_4);
                    break;
                case R.id.rbtn_5:
                    layoutShowTimeView.setVisibility(View.GONE);
                    radioGroup.check(R.id.rbtn_5);
                    break;
                default:
                    break;
            }
        });
    }

    private void initTimePick() {
        //时间选择器
        pvTime = new TimePickerBuilder(CreateTaskActivity.this, (date, v) -> {
            CharSequence str = DateFormat.format("yyyy-MM-dd HH:mm:ss", date.getTime());
            if (flag.equals("start")) {
                tvStartTime.setText(str);
            } else {
                tvEndTime.setText(str);
            }

        }).setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .build();
    }

    /**
     * 新增故障单
     */
    private void createWorkForm() {
        //创建任务 故障和应急必须填写时间才行，其他的不用限制
        if (radioGroup.getCheckedRadioButtonId() == R.id.rbtn_1 || radioGroup.getCheckedRadioButtonId() == R.id.rbtn_2){
            //判断开始时间和结束时间是否选择
            if (tvStartTime.getText().toString().equals("请选择")||tvEndTime.getText().toString().equals("请选择")){
               //提示需要选择之后才能提交
               ToastUtils.showShort("需要选择计划时间之后才能提交");
               return;
            }
        }else if (radioGroup.getCheckedRadioButtonId() == -1){
            ToastUtils.showShort("请选择任务类型");
            return;
        }

        if (et1.getText().toString().trim().length() == 0 || et2.getText().toString().trim().length() == 0) {
            ToastUtils.showShort("请填写完名称和内容再尝试提交");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("taskName", et1.getText().toString().trim());
            jsonObject.put("taskContent", et2.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //通过gson转一下解决JSONObject会自动嵌套一层nameValuePairs的问题
        Gson gson = new Gson();
        JsonElement element = gson.fromJson(String.valueOf(jsonObject), JsonElement.class);
        RxHttp.postBody(Url.task_create)
                .setBody(element)
                .asResponse(String.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //创建成功关闭页面
                    ToastUtils.showShort("创建成功");
                    EventBus.getDefault().post(new RefreshTaskDoingEvent());
                    finish();
                }, throwable -> ToastUtils.showShort(ErrorUtils.whichError(Objects.requireNonNull(throwable.getMessage()))));

    }

}
