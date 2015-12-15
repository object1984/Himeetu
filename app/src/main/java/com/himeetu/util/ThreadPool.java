package com.himeetu.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhangxinjian on 15/9/25.
 */
public class ThreadPool {
    private Executor executor = null;

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    private static ThreadPool instance;

    private ThreadPool() {
        executor = Executors.newSingleThreadExecutor();
    }

    /**
     * 非线程安全
     *
     * @return
     */
    public static ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }

        return instance;
    }
}
