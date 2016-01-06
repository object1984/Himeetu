package com.himeetu.ui.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.event.PhotoEvent;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.LogUtil;
import com.himeetu.util.PhotoUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.HiSurfaceView;

import java.io.File;

/**
 * Created by object1984 on 15/12/21.
 */
public class TakePhotoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TakePhotoActivity";
    private Uri uri;
    private boolean enable = true;
    private HiSurfaceView hiSurfaceView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_photo_take);
        setupToolbar(true, R.string.photo_take);
        init();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        //必须放在onResume中，不然会出现Home键之后，再回到该APP，黑屏
        hiSurfaceView = new HiSurfaceView(getApplicationContext());
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(hiSurfaceView);
    }


    @Override
    protected void setupListeners() {
        super.setupListeners();
        findViewById(R.id.btn_photo_take).setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }



    private void cancel() {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_photo_take:
                    hiSurfaceView.takePicture();
                    NavHelper.toPhotoTakeResultPage(this);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
