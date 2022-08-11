package com.leidi.lteapp.base;

import android.app.Application;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.callback.IConverter;
import rxhttp.wrapper.converter.GsonConverter;
import rxhttp.wrapper.param.Method;
import rxhttp.wrapper.ssl.HttpsUtils;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initHttpRequest();
    }

    private void initHttpRequest() {
        IConverter converter = GsonConverter.create();
        RxHttpPlugins.init(getDefaultOkHttpClient())
                .setDebug(true)
                .setConverter(converter)
                .setOnParamAssembly(param -> {
                    Method method = param.getMethod();
//                    if (method.isGet()) {     //可根据请求类型添加不同的参数
//                    } else if (method.isPost()) {
//                    }
                    return param.add("versionName", "1.0.0")//添加公共参数
                            .addHeader("deviceType", "android"); //添加公共请求头
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
