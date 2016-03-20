package com.himeetu.ui.share;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.model.Talk;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.login.LoginActivity;

/**
 * Created by object1984 on 15/12/23.
 */
public class ShareActivity extends BaseVolleyActivity implements View.OnClickListener {
    private ImageView addImageView;
    private Uri photoUri;
    private TextView talkNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        setStatusBarColor(R.color.black);
        setupToolbar(true, R.string.share_to);

        photoUri = (Uri) getIntent().getExtras().get(Argument.PHOTO_URI);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        addImageView = (ImageView)findViewById(R.id.img_add);
        talkNameTextView = (TextView) findViewById(R.id.text_talk_name);
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

        findViewById(R.id.text_add_talk).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_add_talk:
                addTalk();
                break;
        }
    }

    private void addTalk() {
        NavHelper.toChooseTalk(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            Talk chooseTalk  = (Talk) data.getSerializableExtra(Argument.TALK);
            talkNameTextView.setVisibility(View.VISIBLE);
            talkNameTextView.setText( String.format("#%s", chooseTalk.getName()));
        }
    }
}

