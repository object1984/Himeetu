package com.himeetu.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.himeetu.R;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;

/**
 * Created by object1984 on 15/12/23.
 */
public class ShareActivity extends BaseVolleyActivity {
    private ImageView addImageView;
    private Uri photoUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_share);
        setupToolbar(true, R.string.share_to);

        photoUri = (Uri) getIntent().getExtras().get(Argument.PHOTO_URI);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        addImageView = (ImageView)findViewById(R.id.img_add);
    }

    @Override
    protected void initViews() {
        super.initViews();

        if(photoUri != null){
            addImageView.setImageURI(photoUri);
        }
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
    }
}

