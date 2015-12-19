package com.himeetu.ui.guide;

import android.os.Bundle;
import android.os.Handler;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;


/**
 * Created by object1984 on 15/11/23.
 */
public class GuideActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                NavHelper.toMainPage(GuideActivity.this, "");
                finish();
            }
        }, 1000);
    }

    @Override
    protected void loadViews() {
        super.loadViews();


    }
}
