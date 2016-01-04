package com.himeetu.ui.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;

public class UserActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        String uid = getIntent().getStringExtra("uid");
        MeFragment fragment = MeFragment.newInstance("", "", uid);
        transaction.add(R.id.fragment, fragment);
        transaction.commit();
    }
}
