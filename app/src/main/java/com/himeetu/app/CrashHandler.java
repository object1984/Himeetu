package com.himeetu.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import android.widget.Toast;

import com.himeetu.util.FileUtil;

import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类来接管程序,并记录发送错误报告.
 *
 */
public class CrashHandler implements UncaughtExceptionHandler {

    public static final String TAG = "CrashHandler";

    // CrashHandler 实例
    private static CrashHandler INSTANCE = new CrashHandler();

    // 程序的 Context 对象
    private MEETApplication mContext;

    // 系统默认的 UncaughtException 处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    // 用来存储设备信息和异常信息
    private SimpleArrayMap<String, String> infos = new SimpleArrayMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");


    /**
     * 保证只有一个 CrashHandler 实例
     */
    private CrashHandler() {
    }

    /**
     * 获取 CrashHandler 实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(MEETApplication context) {
        mContext = context;

        // 获取系统默认的 UncaughtException 处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        // 设置该 CrashHandler 为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当 UncaughtException 发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }

            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理，收集错误信息，发送错误报告等操作均在此完成
     *
     * @param ex
     * @return true：如果处理了该异常信息；否则返回 false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        // 使用 Toast 来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉，程序出现异常，即将退出。", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        // 收集设备参数信息
        collectDeviceInfo(mContext);

        // 保存日志文件
//        saveCrashInfo2File(ex);
        FileUtil.saveException(ex, infos);
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);

            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

//    /**
//     * 保存错误信息到文件中
//     * *
//     *
//     * @param ex
//     * @return 返回文件名称, 便于将文件传送到服务器
//     */
//    private String saveCrashInfo2File(Throwable ex) {
//        StringBuffer sb = new StringBuffer();
//        for (Map.Entry<String, String> entry : infos.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            sb.append(key + "=" + value + "\n");
//        }
//
//        Writer writer = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(writer);
//        ex.printStackTrace(printWriter);
////        Throwable cause = ex.getCause();
////        while (cause != null) {
////            cause.printStackTrace(printWriter);
////            cause = cause.getCause();
////        }
//        printWriter.close();
//
//        String result = writer.toString();
//        sb.append(result);
//
//        FileOutputStream fos = null;
//        try {
//            long timestamp = System.currentTimeMillis();
//
//            String fileName = Constants.CRASH_FILE_NAME_PREFFIX + timestamp + ".log";
//
//            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                String filePath = Constants.FILE_DIR_CRASH + fileName;
//                File dir = new File(Constants.FILE_DIR_CRASH);
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//                fos = new FileOutputStream(filePath);
//
//                fos.write(sb.toString().getBytes());
////                fos.close();
////                ConfigUtil.saveValue(Constants.CONFIG_CRASH_FLAG, true);
//            }
//
//            LogUtil.loge(TAG, "Crash error!!!!", ex);
//            return fileName;
//        } catch (Exception e) {
//            Log.e(TAG, "an error occured while writing file...", e);
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    Log.e(TAG, "an error occured while cloase fileout stream...", e);
//                }
//            }
//        }
//
//        return null;
//    }
}
