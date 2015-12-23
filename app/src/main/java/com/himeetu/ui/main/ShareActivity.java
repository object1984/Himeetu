package com.himeetu.ui.main;

import android.os.Bundle;

import com.himeetu.R;
import com.himeetu.ui.base.BaseVolleyActivity;

/**
 * Created by object1984 on 15/12/23.
 */
public class ShareActivity extends BaseVolleyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_share);
        setupToolbar(true, R.string.share_to);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
    }
}

