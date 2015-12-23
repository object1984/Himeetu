package com.himeetu.ui.main;

import android.os.Bundle;
import android.view.View;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseVolleyActivity;

/**
 * Created by object1984 on 15/12/23.
 */
public class SearchActivity extends BaseVolleyActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
        findViewById(R.id.text_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_cancel:
                cancel();
                break;
        }
    }

    private void cancel(){
        NavHelper.finish(this);
    }
}
