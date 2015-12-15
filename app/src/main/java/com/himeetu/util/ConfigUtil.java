package com.himeetu.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.himeetu.app.MEETApplication;


/**
 * 配置文件操作
 * SharedPreferences 作为dao层
 */
public class ConfigUtil {
    public static String getStringValue(String key, String defValue) {
        return ConfigUtil.getSharedPreference().getString(key, defValue);
    }

    public static String getStringValue(String key) {
        return ConfigUtil.getStringValue(key, null);
    }

    public static int getIntValue(String key, int defValue) {
        return getSharedPreference().getInt(key, defValue);
    }

    public static int getIntValue(String key) {
        return ConfigUtil.getIntValue(key, 0);
    }

    public static boolean getBooleanValue(String key, boolean defValue) {
        return getSharedPreference().getBoolean(key, defValue);
    }

    public static boolean getBooleanValue(String key) {
        return getBooleanValue(key, false);
    }

    public static long getLongValue(String key) {
        return getSharedPreference().getLong(key, 0);
    }

    public static void saveValue(String key, Object value) {
        Editor editor = getSharedPreference().edit();
        if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else {
            editor.putString(key, (String) value);
        }
        editor.commit();
    }

    public static void saveStringValue(String key, String value) {
        Editor editor = getSharedPreference().edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static void remove(String... keys) {
        Editor editor = getSharedPreference().edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.commit();
    }

    public static SharedPreferences getSharedPreference() {
        return MEETApplication.getInstance().getSharedPreference();

    }


}
