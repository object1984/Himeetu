package com.himeetu.ui.setup;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.main.MainActivity;
import com.himeetu.view.SelectPicPopupWindow;

import java.util.List;

/**
 * 设置界面
 */
public class SettingsActivity extends BaseActivity implements OnClickListener {


    private RelativeLayout rlFacebook;
    private RelativeLayout rlWeixin;
    private RelativeLayout rlContacts;
    private RelativeLayout rlInviteFriends;
    private RelativeLayout rlDataEdit;
    private RelativeLayout rlPwdEdit;
    private RelativeLayout rlClearCache;
    private TextView tvLogout;
    private SelectPicPopupWindow menuWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        initToolBar();

        init();

    }

    private void initToolBar() {
        setupToolbar(true, R.string.setting);
        setToolBarColor(getResources().getColor(R.color.white));
    }


    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        rlFacebook = (RelativeLayout) findViewById(R.id.rl_facebook);
        rlWeixin = (RelativeLayout) findViewById(R.id.rl_weixin);
        rlContacts = (RelativeLayout) findViewById(R.id.rl_contacts);
        rlInviteFriends = (RelativeLayout) findViewById(R.id.rl_invite_friends);
        rlDataEdit = (RelativeLayout) findViewById(R.id.rl_data_edit);
        rlPwdEdit = (RelativeLayout) findViewById(R.id.rl_pwd_edit);
        rlClearCache = (RelativeLayout) findViewById(R.id.rl_clear_cache);
        tvLogout = (TextView) findViewById(R.id.tv_logout);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        rlFacebook.setOnClickListener(this);
        rlWeixin.setOnClickListener(this);
        rlContacts.setOnClickListener(this);
        rlInviteFriends.setOnClickListener(this);
        rlDataEdit.setOnClickListener(this);
        rlPwdEdit.setOnClickListener(this);
        rlClearCache.setOnClickListener(this);
        tvLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_invite_friends:

                startActivity(new Intent(SettingsActivity.this, InviteFriendsActivity.class));

                break;

            case R.id.tv_logout:

                showLogout();

                break;
            case R.id.rl_data_edit:

                startActivity(new Intent(SettingsActivity.this, EditUserDetailActivity.class));

                break;
            case R.id.rl_pwd_edit:

                startActivity(new Intent(SettingsActivity.this, EditPassWordActivity.class));
                break;

        }
    }

    /**
     * 显示退出pop
     */
    private void showLogout() {

        menuWindow = new SelectPicPopupWindow(SettingsActivity.this, new OnClickListener() {

            public void onClick(View v) {
                menuWindow.dismiss();
                //退出登录


            }

        });

        menuWindow.setTextColor(0,getResources().getColor(R.color.red), getResources().getColor(R.color.text_dark_black));
        menuWindow.setText(0,R.string.logout, R.string.cancel);

        //显示窗口
        menuWindow.showAtLocation(SettingsActivity.this.findViewById(R.id.tv_logout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


    }
}