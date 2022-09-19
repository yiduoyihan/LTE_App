package com.leidi.lteapp.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.AppUtils;
import com.leidi.lteapp.ui.CheckUpdateActivity;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownLoadUtil {
   public static String TAG = "downloadUtil";
   public static String destPath = Environment.getExternalStorageDirectory() + "/Download/html/lte_gz.apk";
    public static void sendRequestWithOkHttp(Activity context , String url, LoadingDialog loadingDialog) {
        loadingDialog.setLoadingText("下载中").show();
        new Thread(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Response response = client.newCall(request).execute();
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "request permission");
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    Log.d(TAG, "has permission");
                }
                File file = new File(destPath);
                if (file.exists()) {
                    Log.d(TAG, "file exist");
                }
                InputStream inputStream;
                inputStream = response.body().byteStream();
                Log.d(TAG, "write start2 ");
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(0);
                byte[] buf = new byte[1024];
                int len = 0;
                Log.d(TAG, "write start ");
                while ((len = inputStream.read(buf)) != -1) {
                    Log.d(TAG, "write len " + len);
                    randomAccessFile.write(buf, 0, len);
                }
                response.body().close();
                randomAccessFile.close();
                context.runOnUiThread(() -> loadingDialog.closeSuccessAnim().loadSuccess());
                AppUtils.installApp(destPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}