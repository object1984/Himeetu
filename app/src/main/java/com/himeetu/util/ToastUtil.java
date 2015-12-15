package com.himeetu.util;

import android.widget.Toast;

import com.himeetu.app.MEETApplication;

/**
 * Created by object1984 on 2015/5/18.
 */
public class ToastUtil {
    public static void show(String msg) {
        Toast.makeText(MEETApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void show(int strResId) {
        Toast.makeText(MEETApplication.getInstance(), MEETApplication.getInstance().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }
}
