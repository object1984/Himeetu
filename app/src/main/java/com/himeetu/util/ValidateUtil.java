package com.himeetu.util;

import android.text.TextUtils;
import android.widget.Toast;


import com.himeetu.R;
import com.himeetu.app.MEETApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ylsg365 on 2015-02-08.
 */
public class ValidateUtil {
    public static boolean checkUserName(String username) {
        boolean result = true;
        int noticeId = -1;

        if (TextUtils.isEmpty(username)) {
            noticeId = R.string.notice_name_blank;
            result = false;
        }

//        if(!isEmail(username) &&  !isPhoneNumber(username)){
//            noticeId = R.string.notice_name_format;
//            result = false;
//        }

        if (-1 != noticeId) {
            Toast.makeText(MEETApplication.getInstance(), MEETApplication.getInstance().getResources().getString(noticeId), Toast.LENGTH_SHORT).show();
        }

        return result;
    }

    public static boolean checkPassword(String password) {
        boolean result = true;
        int noticeId = -1;

        if (TextUtils.isEmpty(password)) {
            noticeId = R.string.notice_password_blank;
            result = false;
        }

        if(password.length()< 6 || password.length() > 20){
            noticeId = R.string.notice_password_length;
            result = false;
        }
        if (-1 != noticeId) {
            Toast.makeText(MEETApplication.getInstance(), MEETApplication.getInstance().getResources().getString(noticeId), Toast.LENGTH_SHORT).show();
        }

        return result;
    }

//    public static boolean checkUserPasswordForLogin(String password) {
//        boolean result = true;
//        int noticeId = -1;
//
//        if (StringUtils.isBlank(password)) {
//            noticeId = R.string.notice_password_blank;
//            result = false;
//        }
//
//        if (-1 != noticeId) {
//            Toast.makeText(EBYApplication.getInstance(), EBYApplication.getInstance().getResources().getString(noticeId), Toast.LENGTH_SHORT).show();
//        }
//
//        return result;
//    }
//
//    public static boolean checkUserPasswordForUpdate(String password) {
//        boolean result = true;
//        int noticeId = -1;
//
//        if (StringUtils.isBlank(password)) {
//            noticeId = R.string.notice_password_blank;
//            result = false;
//        } else if (!hasNumberAndLetter(password)) {
//            noticeId = R.string.notice_password_rule;
//            result = false;
//        } else if (password.length() < 8 || password.length() > 20) {
//            noticeId = R.string.notice_password_length;
//            result = false;
//        }
//
//        if (-1 != noticeId) {
//            Toast.makeText(EBYApplication.getInstance(), EBYApplication.getInstance().getResources().getString(noticeId), Toast.LENGTH_SHORT).show();
//        }
//
//        return result;
//    }
//
//    public static boolean checkUserPasswordConfirm(String password, String confrimPassword) {
//        boolean result = true;
//        int noticeId = -1;
//
//        if (StringUtils.isBlank(password)) {
//            noticeId = R.string.notice_password_blank;
//            result = false;
//        } else if (password.length() < 8 || password.length() > 20) {
//            noticeId = R.string.notice_password_length;
//            result = false;
//        } else if (!password.equals(confrimPassword)) {
//            noticeId = R.string.notice_password_confirm_error;
//            result = false;
//        }
//
//        if (-1 != noticeId) {
//            Toast.makeText(EBYApplication.getInstance(), EBYApplication.getInstance().getResources().getString(noticeId), Toast.LENGTH_SHORT).show();
//        }
//
//        return result;
//    }

    public static boolean isPhoneNumber(String phoneNum) {
        String expression = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        CharSequence inputStr = phoneNum;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean hasNumberAndLetter(String password) {
        String expression = "[\\da-zA-Z]+";
        CharSequence inputStr = password;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 正则表达式 判断邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
