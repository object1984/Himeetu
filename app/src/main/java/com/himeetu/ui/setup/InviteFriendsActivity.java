package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.TextViewUtil;

/**
 * 邀请码
 */
public class InviteFriendsActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_id;
    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvRightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);
        super.init();
        tv_id = (TextView) findViewById(R.id.tv_id);

    }


    @Override
    protected void loadViews() {
        super.loadViews();

        ivBack = (ImageView) findViewById(R.id.toolbar_back);
        tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvRightText = (TextView) findViewById(R.id.toolbar_right_text);


    }

    @Override
    protected void initViews() {
        super.initViews();

        tvTitle.setText(getString(R.string.invitation));
        tvRightText.setText(getString(R.string.share));


    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        ivBack.setOnClickListener(this);
        tvRightText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.toolbar_back:
                NavHelper.finishWithAnim(InviteFriendsActivity.this);
                onBackPressed();

                break;
            case R.id.toolbar_right_text:  //分享


                break;
        }


    }
}
