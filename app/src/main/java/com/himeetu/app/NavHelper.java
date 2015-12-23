package com.himeetu.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.himeetu.R;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.login.FindPasswordActivity;
import com.himeetu.ui.login.LoginActivity;
import com.himeetu.ui.main.MainActivity;
import com.himeetu.ui.main.SearchActivity;
import com.himeetu.ui.main.ShareActivity;
import com.himeetu.ui.main.TopicDetailsActivity;
import com.himeetu.ui.photo.PhotoMainActivity;
import com.himeetu.ui.photo.TakePhotoActivity;
import com.himeetu.ui.register.CountryChooseActivity;
import com.himeetu.ui.register.IdentityActivity;
import com.himeetu.ui.register.RegisterActivity;
import com.himeetu.util.LogUtil;


public class NavHelper {

    public static void toLoginPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, LoginActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toFindPassowrdPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, FindPasswordActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toMainPage(Activity activity, String username) {
        Intent intent = new Intent();
        intent.putExtra(Argument.USERNAME, username);
        intent.setClass(activity, MainActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toRegisterPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }

    public static void toCountryChoosePage(Activity activity, String username, String password) {
        Intent intent = new Intent();
        intent.putExtra(Argument.USERNAME, username);
        intent.putExtra(Argument.PASSWORD, password);
        intent.setClass(activity, CountryChooseActivity.class);
        activity.startActivity(intent);
    }
    public static void toIdentityPage(Activity activity, String username, String password, int countryCode) {
        Intent intent = new Intent();
        intent.putExtra(Argument.USERNAME, username);
        intent.putExtra(Argument.PASSWORD, password);
        intent.putExtra(Argument.COUNTRY_CODE, countryCode);
        intent.setClass(activity, IdentityActivity.class);
        activity.startActivity(intent);
    }
    public static void toPhotoMainPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, PhotoMainActivity.class);
        activity.startActivity(intent);
        forwardFadeInAnim(activity);
    }

    public static void toPhotoTakePage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TakePhotoActivity.class);
        activity.startActivity(intent);
//        forwardFadeInAnim(activity);
    }

    public static void toTalkDetailPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TopicDetailsActivity.class);
        activity.startActivity(intent);
//        forwardFadeInAnim(activity);
    }

    public static void toSharePage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ShareActivity.class);
        activity.startActivity(intent);
//        forwardFadeInAnim(activity);
    }

    public static void toSearchPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, SearchActivity.class);
        activity.startActivity(intent);
//        forwardFadeInAnim(activity);
    }

    public static void backMainPage(Activity activity, String target) {
        Intent intent = new Intent();
        intent.putExtra(Argument.TARGET, target);
        intent.setClass(activity, MainActivity.class);
        activity.startActivity(intent);
        backAnim(activity);
    }

    public static void forwardAnim(Activity activity, int animInId, int animOutId) {
        activity.overridePendingTransition(animInId, animOutId);
    }

    public static void forwardAnim(Activity activity) {
        forwardAnim(activity, R.anim.inktv_mysonglist_anim_show_in, R.anim.inktv_mysonglist_anim_show_out);
    }

    public static void backAnim(Activity activity){
        activity.overridePendingTransition(R.anim.inktv_mysonglist_anim_hide_in, R.anim.inktv_mysonglist_anim_hide_out);
    }

    public static void finishWithAnim(Context context, int result, Intent intent) {
        ((Activity) context).setResult(result, intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.inktv_mysonglist_anim_hide_in, R.anim.inktv_mysonglist_anim_hide_out);
    }

    public static void finishWithAnim(Context context) {
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.inktv_mysonglist_anim_hide_in, R.anim.inktv_mysonglist_anim_hide_out);
    }

    public static void finish(Context context) {
        ((Activity) context).finish();
    }

    public static void forwardFadeInAnim(Activity activity) {
        forwardAnim(activity, R.anim.fade_in, 0);
    }

    public static void forwardBottomInAnim(Activity activity) {
        forwardAnim(activity, R.anim.slide_in_bottom, 0);
    }
    public static void forwardBottomOutAnim(Activity activity) {
        forwardAnim(activity, 0, R.anim.inktv_remote_control_anim_hide_out);
    }

    public static void finishWithBottomOutAnim(Context context, int result, Intent intent) {
        ((Activity) context).setResult(result, intent);
        ((Activity) context).finish();
        forwardBottomOutAnim((Activity) context);
    }

}
