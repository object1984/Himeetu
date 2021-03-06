package com.himeetu.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.himeetu.network.dic.UrlPatten;
import com.himeetu.network.http.MultiPartStack;
import com.himeetu.network.http.OkHttpStack;
import com.himeetu.network.volley.PersistentCookieStore;
import com.himeetu.util.FileUtil;
import com.himeetu.util.LogUtil;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by object1984 on 2015/5/14.
 */
public class MEETApplication extends Application {
    private static final String TAG = "MEETApplication";
    private static MEETApplication instance;
    protected volatile SharedPreferences sharedPreference;


    @Override
    public void onCreate() {
        super.onCreate();
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);


        instance = this;
        init();

//        EBYApi.uploadCrashLog();
    }

    /**
     * @return 返回context，能够动态获取资源（在任意位置获取应用程序Context）
     */
    public static MEETApplication getInstance() {
        return instance;
    }

    private void init() {

        sharedPreference = getSharedPreferences(Constants.CONFIG_FILE_NAME, MODE_PRIVATE);
        try {
            String mainFolder = getBasePath();
            FileUtil.createFolder(mainFolder);
        } catch (Exception e) {
            e.printStackTrace();
        }


        String folder = Environment.getExternalStorageDirectory().getAbsolutePath();

        FileUtil.mkdirs(folder + "/himeetu");


    }




    public int getWindowWidth() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;

        return w_screen;
    }

    /**
     * 网络是否连接通
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    public boolean isMobileConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mMobileNetworkInfo != null)
            return mMobileNetworkInfo.isConnected();
        return false;
    }

    public boolean isWifiConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (mWiFiNetworkInfo != null)
            return mWiFiNetworkInfo.isConnected();
        return false;
    }


    public SharedPreferences getSharedPreference() {
        return sharedPreference;
    }

    public File getBaseFile() {
        return new File(getBasePath());
    }

    /**
     * @return sd卡的根目录 + /eby
     */
    public String getBasePath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {//sd卡存在
            File file = Environment.getExternalStorageDirectory();
            if (file != null) {
                return file.getAbsoluteFile() + Constants.CONFIG_FOLDER;
            }
        }
        return this.getFilesDir().getAbsolutePath();
    }

    private RequestQueue mRequestQueue;
//    private RequestQueue mUploadQueue;
    public RequestQueue getVolleyRequestQueue() {
        OkHttpClient okHttpClient =null;
        if (mRequestQueue == null)
        {
             okHttpClient = new OkHttpClient();
//            CookieManager cookieManager = new CookieManager();
//
//            CookieStore cookieStore = cookieManager.getCookieStore();
//                for(HttpCookie cookie :  cookieStore.getCookies()){
//                    LogUtil.d("cookie", String.format("name=%s, value=%s", cookie.getName(), cookie.getValue()));
//                }
//
//            okHttpClient.setCookieHandler(cookieManager);

            okHttpClient.setCookieHandler(new CookieManager(
                    new PersistentCookieStore(getApplicationContext()),
                    CookiePolicy.ACCEPT_ALL));

            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack(okHttpClient));
        }
        return mRequestQueue;
    }

//    public RequestQueue getVolleyUploadQueue() {
//        if (mUploadQueue == null)
//        {
//            OkHttpClient okHttpClient = new OkHttpClient();
//            CookieManager cookieManager = new CookieManager();
//
//            CookieStore cookieStore = cookieManager.getCookieStore();
//            for(HttpCookie cookie :  cookieStore.getCookies()){
//                LogUtil.d("cookie", String.format("name=%s, value=%s", cookie.getName(), cookie.getValue()));
//            }
//
//            okHttpClient.setCookieHandler(cookieManager);
//
//            mUploadQueue =  Volley.newRequestQueue(this, new MultiPartStack());
//        }
//        return mUploadQueue;
//    }


    public static void addRequest(@NonNull final Request<?> request){
        getInstance().getVolleyRequestQueue().add(request);
    }

    public static void addRequest(@NonNull final Request<?> request,@NonNull final String tag){
        request.setTag(tag);

        LogUtil.d("url.tag＝" + tag, request.getUrl());
        getInstance().getVolleyRequestQueue().add(request);
    }

    public static void cancelAllRequests(@NonNull final String tag){
        if(getInstance().getVolleyRequestQueue() != null){
            getInstance().getVolleyRequestQueue().cancelAll(tag);
        }
    }


//    public static void addUploadRequest(@NonNull final Request<?> request,@NonNull final String tag){
//        request.setTag(tag);
//
//        LogUtil.d("url.tag＝" + tag, request.getUrl());
//        getInstance().getVolleyUploadQueue().add(request);
//    }
}
