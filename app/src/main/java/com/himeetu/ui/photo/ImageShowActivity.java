package com.himeetu.ui.photo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by object1984 on 16/3/8.
 */
public class ImageShowActivity extends BaseVolleyActivity {
    private String imgPath = "";
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_img_big);
        setStatusBarColor(R.color.black);

        imgPath = getIntent().getStringExtra("img");
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        imageView = (ImageView)findViewById(R.id.img_big);

        Picasso.with(this).load(imgPath).placeholder(R.drawable.img_default)
                .error(R.drawable.img_default).into(imageView);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHelper.finishWithAnimHideOut(ImageShowActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
        NavHelper.finishWithAnimHideOut(this);
    }
}
