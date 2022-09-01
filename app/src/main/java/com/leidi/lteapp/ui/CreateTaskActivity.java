package com.leidi.lteapp.ui;

import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.leidi.lteapp.R;
import com.leidi.lteapp.base.BaseActivity;
import com.leidi.lteapp.base.BaseBean;
import com.leidi.lteapp.event.RefreshTaskDoingEvent;
import com.leidi.lteapp.util.Constant;
import com.leidi.lteapp.util.SpUtilsKey;
import com.leidi.lteapp.util.Url;
import com.rxjava.rxlife.RxLife;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

/**
 * 创建故障单页面
 * @author 阎
 */
public class CreateTaskActivity extends BaseActivity {
    EditText et1, et2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_task;
    }

    @Override
    protected void initView() {
        long sysTime = System.currentTimeMillis();
        CharSequence sysTimeStr = DateFormat.format("yyyy-MM-dd HH:mm:ss", sysTime);
        setToolbar("创建故障单");
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
    }

    /**
     * 新增故障单
     */
    private void createWorkForm() {
        System.out.println("=====token:Bearer "+SPUtils.getInstance().getString(SpUtilsKey.TOKEN));;
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
                .asClass(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .to(RxLife.to(this))
                .subscribe(bean -> {
                    //请求成功
                    if (bean.getCode() == Constant.SUCCESS_CODE) {
                        //创建成功关闭页面
                        EventBus.getDefault().post(new RefreshTaskDoingEvent());
                        finish();
                    } else {
                        ToastUtils.showShort(bean.getMsg());
                    }
                }, throwable -> System.out.println(throwable.getMessage()));

    }

}
