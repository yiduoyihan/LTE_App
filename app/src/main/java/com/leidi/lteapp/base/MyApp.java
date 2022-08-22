package com.leidi.lteapp.base;

import android.app.Application;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.leidi.lteapp.util.SpUtilsKey;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.callback.IConverter;
import rxhttp.wrapper.converter.GsonConverter;
import rxhttp.wrapper.param.Method;
import rxhttp.wrapper.ssl.HttpsUtils;

public class MyApp extends Application {

    public static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initHttpRequest();
    }

    protected void initHttpRequest() {
        System.out.println("====application:onCreate");
        IConverter converter = GsonConverter.create();
        RxHttpPlugins.init(getDefaultOkHttpClient())
                .setDebug(true)
                .setConverter(converter)
                .setOnParamAssembly(param -> {
                    Method method = param.getMethod();
//                    if (method.isGet()) {     //可根据请求类型添加不同的参数
//                    } else if (method.isPost()) {
//                    }
                    return param.add("version", AppUtils.getAppVersionName())//添加公共参数
                            .addHeader("Authorization", "Bearer " + SPUtils.getInstance().getString(SpUtilsKey.TOKEN));
                    //添加公共请求头
                });
    }

    //Default OkHttpClient object in RxHttp
    private static OkHttpClient getDefaultOkHttpClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

}
