package com.himeetu.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;

import com.himeetu.util.FileUtil;

import java.io.File;


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

        FileUtil.mkdirs(folder + "/test");


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


}
