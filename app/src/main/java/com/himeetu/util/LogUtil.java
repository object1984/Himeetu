package com.himeetu.util;

import android.os.Environment;
import android.util.Log;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class LogUtil {
    private static final String TAG = "LogUtil";

    private static final String SUFFIX = ".log";
    private static final String FOLDER = "log";

    public static boolean debug = true;

    private static ExecutorService pool = Executors.newSingleThreadExecutor();

    public static void logi(String tag, String msg) {
        if (!debug) {
            return;
        }
        Log.i(tag, "" + msg);
    }

    public static void d(String tag, String msg) {
        if (!debug) {
            return;
        }
        Log.d(tag, "" + msg);
//        pool.execute(new LogThread(tag, msg));
    }

    public static void loge(String tag, String msg) {
        if (!debug) {
            return;
        }
        Log.e(tag, "" + msg);
    }

    public static void logw(String tag, String msg) {
        if (!debug) {
            return;
        }
        Log.w(tag, "" + msg);
    }

    /**
     * 打印日志，异常
     *
     * @param tag
     * @param msg
     * @param e
     */
    public static void loge(String tag, String msg, Throwable e) {
        if (!debug) {
            return;
        }

        Log.e(tag, msg, e);
    }


    static class LogThread extends Thread {
        private String tag;
        private String message;

        public LogThread(String tag, String message) {
            this.tag = tag;
            this.message = message;
        }

        @Override
        public void run() {
            super.run();
            File fileFolder = new File(Environment.getExternalStorageDirectory()
                    + "/himeetu/log");
            if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                fileFolder.mkdir();
            }

            String logFileName = DateUtils.getDateyyyyMMdd() + SUFFIX;
            File logFile = new File(fileFolder, logFileName);
            if (!logFile.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                try {
                    logFile.createNewFile();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            BufferedWriter bufferedWriter = null;
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true), "utf-8"));


                String log = String.format("[%s]  %s %s", DateUtils.getDate(), tag, message);
                bufferedWriter.append(log);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
