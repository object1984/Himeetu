package com.himeetu.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangxinjian on 15/9/21.
 */
public class StringUtil {
    /**
     * 合并字符串
     *
     * @param args 待合并的字符串数组
     * @return
     */
    public static String join(String... args) {
        if (args == null || args.length == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            sb.append(arg);
        }

        return sb.toString();
    }

    private static Pattern urlPattern = Pattern.compile("http[s]*://.*");

    /**
     * 判断URL地址是否有效
     *
     * @param url
     * @return true有效，false无效
     */
    public static boolean isValidUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        Matcher matcher = urlPattern.matcher(url);
        return matcher.matches();
    }
}
