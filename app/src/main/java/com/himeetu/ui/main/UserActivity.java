package com.himeetu.ui.main;

import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.StatusBarCompat;

/**
 *其他用户的主页
 */
public class UserActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setStatusBarColor(R.color.black);
        setupToolbar(true, 0);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String uid = getIntent().getStringExtra("uid");
        MeFragment fragment = MeFragment.newInstance("", "", uid);
        transaction.add(R.id.fragment, fragment);
        transaction.commit();


    }


}
