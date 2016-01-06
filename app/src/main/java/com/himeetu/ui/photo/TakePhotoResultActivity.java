package com.himeetu.ui.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.event.PhotoEvent;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by object1984 on 15/12/21.
 */
public class TakePhotoResultActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "TakePhotoActivity";

    private Uri uri;
    private ImageView photoImageView;
    private ImageButton commitImageButton;
    private ImageButton resetImageButton;
    private ImageButton albumImageButton;
    private boolean enable = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_photo_take_result);
        setupToolbar(true, R.string.photo_take);
        init();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        photoImageView = (ImageView)findViewById(R.id.img_photo);
        commitImageButton = (ImageButton)findViewById(R.id.btn_photo_commit);
        resetImageButton = (ImageButton)findViewById(R.id.btn_photo_reset);
        albumImageButton = (ImageButton)findViewById(R.id.btn_photo_album);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        commitImageButton.setOnClickListener(this);
        resetImageButton.setOnClickListener(this);
        albumImageButton.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_photo_commit:
                commit();
                break;
        }
    }

    public void onEventMainThread(PhotoEvent photoEvent){
        if(photoImageView != null){
            uri = photoEvent.fileUri;
            photoImageView.setImageURI(photoEvent.fileUri);
        }
    }

    private void commit(){
        NavHelper.toSharePage(this, uri);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
