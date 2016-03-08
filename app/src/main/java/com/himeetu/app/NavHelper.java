package com.himeetu.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.himeetu.R;
import com.himeetu.model.HiActivity;
import com.himeetu.model.Talk;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.login.FindPasswordActivity;
import com.himeetu.ui.login.LoginActivity;
import com.himeetu.ui.main.ActivitiesDetailsActivity;
import com.himeetu.ui.main.MainActivity;
import com.himeetu.ui.main.MeFragment;
import com.himeetu.ui.photo.ImageShowActivity;
import com.himeetu.ui.search.SearchMoreUserActivity;
import com.himeetu.ui.main.UserActivity;
import com.himeetu.ui.search.SearchActivity;
import com.himeetu.ui.main.ShareActivity;
import com.himeetu.ui.main.TopicDetailsActivity;
import com.himeetu.ui.main.TopicHighlightsActivity;
import com.himeetu.ui.my.AttentionActivity;
import com.himeetu.ui.photo.PhotoMainActivity;
import com.himeetu.ui.photo.TakePhotoActivity;
import com.himeetu.ui.photo.TakePhotoResultActivity;
import com.himeetu.ui.register.CountryChooseActivity;
import com.himeetu.ui.register.IdentityActivity;
import com.himeetu.ui.register.InvitationCodeActivity;
import com.himeetu.ui.register.RegisterActivity;
import com.himeetu.ui.setup.EditPassWordActivity;
import com.himeetu.ui.setup.EditUserDetailActivity;
import com.himeetu.ui.setup.InviteFriendsActivity;
import com.himeetu.ui.setup.SettingsActivity;


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

    public static void toMainPage(Activity activity) {
        Intent intent = new Intent();
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

    public static void toPhotoTakeResultPage(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TakePhotoResultActivity.class);
        activity.startActivity(intent);
//        forwardFadeInAnim(activity);
    }

    public static void toTalkDetailPage(Activity activity, Talk talk) {
        Intent intent = new Intent();
        intent.setClass(activity, TopicDetailsActivity.class);
        intent.putExtra(Argument.TALK, talk);
        activity.startActivity(intent);
//        forwardFadeInAnim(activity);
    }

    public static void toSharePage(Activity activity, Uri photoUri) {
        Intent intent = new Intent();
        intent.putExtra(Argument.PHOTO_URI, photoUri);
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

    public static void toEditPassWordActivity(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, EditPassWordActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toActivityTalksPage(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, TopicHighlightsActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toTopicDetailsPage(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, TopicDetailsActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toActivityDetailPage(Activity activity, HiActivity hiActivity){
        Intent intent = new Intent();
        intent.putExtra(Argument.HIACTIVITY, hiActivity);
        intent.setClass(activity, ActivitiesDetailsActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toEditUserDetailActivity(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, EditUserDetailActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toUserActivity(Activity activity, String uid){
        Intent intent = new Intent();
        intent.setClass(activity, UserActivity.class);
        intent.putExtra("uid", uid);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toSearchMoreUser(Activity activity, String user){
        Intent intent = new Intent();
        intent.setClass(activity, SearchMoreUserActivity.class);
        intent.putExtra("user", user);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toInvitationCodeActivity(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, InvitationCodeActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void toSettingsActivity(Activity activity){
        Intent intent = new Intent();
        intent.setClass(activity, SettingsActivity.class);
        activity.startActivityForResult(intent, 10);
        forwardAnim(activity);
    }

    public static void toImageShowActivity(Activity activity,String img){
        Intent intent = new Intent();
        intent.setClass(activity, ImageShowActivity.class);
        intent.putExtra("img", img);
        activity.startActivity(intent);
        forwardFadeInAnim(activity);
    }

    public static void toAttentionActivity(Activity activity,String id,MeFragment.AttentionType type){
        Intent intent = new Intent();
        intent.setClass(activity, AttentionActivity.class);
        intent.putExtra("id", id);
        intent.putExtra(Argument.ATTENTION_TYPE, type);
        activity.startActivity(intent);
        forwardAnim(activity);
    }

    public static void backMainPage(Activity activity, String target) {
        Intent intent = new Intent();
        intent.putExtra(Argument.TARGET, target);
        intent.setClass(activity, MainActivity.class);
        activity.startActivity(intent);
        backAnim(activity);
    }


    public static void toInviteFriendsActivity(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, InviteFriendsActivity.class);
        activity.startActivity(intent);
        forwardAnim(activity);
    }



    public static void forwardAnim(Activity activity, int animInId, int animOutId) {
        activity.overridePendingTransition(animInId, animOutId);
    }

    public static void forwardAnim(Activity activity) {
        forwardAnim(activity, R.anim.inktv_mysonglist_anim_show_in, R.anim.inktv_mysonglist_anim_show_out);
    }

    public static void forwardAnimShow(Activity activity) {
        forwardAnim(activity, R.anim.inktv_mysonglist_anim_show_in, R.anim.inktv_mysonglist_anim_show_out);
    }

    public static void forwardAnimHide(Activity activity) {
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
    public static void finishWithAnimHideOut(Activity activity) {
        activity.finish();
        forwardFadeOutAnim(activity);
    }

    public static void forwardFadeInAnim(Activity activity) {
        forwardAnim(activity, R.anim.fade_in, 0);
    }

    public static void forwardFadeOutAnim(Activity activity) {
        forwardAnim(activity, 0, R.anim.fade_out);
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
