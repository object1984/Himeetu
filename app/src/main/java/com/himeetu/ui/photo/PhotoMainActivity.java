package com.himeetu.ui.photo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;

/**
 * Created by object1984 on 15/12/17.
 */
public class PhotoMainActivity extends BaseActivity implements View.OnClickListener {
    private View btnsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setThemeTranslucent();
        setContentView(R.layout.activity_photo_main);

        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        btnsView = findViewById(R.id.layout_btns);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        findViewById(R.id.btn_close).setOnClickListener(this);
        findViewById(R.id.btn_photo_take).setOnClickListener(this);
        findViewById(R.id.btn_photo_album).setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animator animator = AnimatorInflater.loadAnimator(PhotoMainActivity.this, R.anim.anim);

                animator.setTarget(btnsView);

                animator.start();
            }
        },200);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_close:
                exit();
                break;
            case R.id.btn_photo_take:
                toPhotoTake();
                break;
            case R.id.btn_photo_album:
                exit();
                break;
        }
    }

    private void exit(){
        NavHelper.finish(this);
    }

    private void toPhotoTake(){
        NavHelper.toPhotoTakePage(this);
    }
}
