package com.leidi.lteapp.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.SPUtils;
import com.leidi.lteapp.bean.DaoMaster;
import com.leidi.lteapp.bean.DaoSession;
import com.leidi.lteapp.util.SpUtilsKey;
import com.tencent.bugly.crashreport.CrashReport;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttpPlugins;
import rxhttp.wrapper.callback.IConverter;
import rxhttp.wrapper.converter.GsonConverter;
import rxhttp.wrapper.param.Method;
import rxhttp.wrapper.ssl.HttpsUtils;

public class MyApp extends Application {

    public static Application instance;
    private static final String DB_NAME = "lte_app.db";
    private static DaoSession mDaoSession;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        closeAndroidPDialog();
        CrashReport.initCrashReport(getApplicationContext(), "54a947a2bc", false);
        CrashReport.setDeviceId(getApplicationContext(), DeviceUtils.getUniqueDeviceId());
        initHttpRequest();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
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
//                    .add("version", AppUtils.getAppVersionName())//添加公共参数
                    return param.addHeader("Authorization", "Bearer " + SPUtils.getInstance().getString(SpUtilsKey.TOKEN));
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

    /**
     * 适配P targetSdkVersion<28 会在最新安卓版本P等都会出现该弹窗提示。调用这个方法消除弹窗
     */
    @SuppressLint("PrivateApi")
    private static void closeAndroidPDialog() {
        try {
            Class<?> aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            java.lang.reflect.Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
