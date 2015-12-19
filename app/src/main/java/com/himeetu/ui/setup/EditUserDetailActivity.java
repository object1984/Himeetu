package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.view.SelectPicPopupWindow;

/**
 * 编辑用户资料
 */
public class EditUserDetailActivity extends BaseActivity implements View.OnClickListener {
    private SelectPicPopupWindow menuWindow;
    private RelativeLayout rlFacebook;
    private ImageView ivArrowsRight;
    private RelativeLayout rlWeixin;
    private RelativeLayout rlContacts;
    private RelativeLayout rlInviteFriends;
    private RelativeLayout rlDataEdit;
    private RelativeLayout rlPwdEdit;
    private RelativeLayout rlClearCache;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_detail);
        super.init();
        initToolBar();

    }


    @Override
    protected void loadViews() {
        super.loadViews();
        rlFacebook = (RelativeLayout) findViewById(R.id.rl_facebook);
        ivArrowsRight = (ImageView) findViewById(R.id.iv_arrows_right);
        rlWeixin = (RelativeLayout) findViewById(R.id.rl_weixin);
        rlContacts = (RelativeLayout) findViewById(R.id.rl_contacts);
        rlInviteFriends = (RelativeLayout) findViewById(R.id.rl_invite_friends);
        rlDataEdit = (RelativeLayout) findViewById(R.id.rl_data_edit);
        rlPwdEdit = (RelativeLayout) findViewById(R.id.rl_pwd_edit);
        rlClearCache = (RelativeLayout) findViewById(R.id.rl_clear_cache);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        rlFacebook.setOnClickListener(this);
        ivArrowsRight.setOnClickListener(this);
        rlWeixin.setOnClickListener(this);
        rlContacts.setOnClickListener(this);
        rlInviteFriends.setOnClickListener(this);
        rlDataEdit.setOnClickListener(this);
        rlPwdEdit.setOnClickListener(this);
        rlClearCache.setOnClickListener(this);

    }

    private void initToolBar() {
        setupToolbar(true, R.string.edit_user_data);
        setRightTextAndVisible(R.string.complete, View.VISIBLE);
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //完成
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {


        }

    }


    /**
     * 显示pop
     */
    private void showLogout() {

        menuWindow = new SelectPicPopupWindow(EditUserDetailActivity.this, new View.OnClickListener() {

            public void onClick(View v) {
                menuWindow.dismiss();

                switch (v.getId()) {

                    case R.id.btn1:

                        break;
                    case R.id.btn2:

                        break;

                }


            }

        });

        menuWindow.setTextColor(getResources().getColor(R.color.text_dark_black), getResources().getColor(R.color.text_dark_black), getResources().getColor(R.color.text_dark_black));
        menuWindow.setText(R.string.take_photos, R.string.from_the_album_to_choose, R.string.cancel);

        //显示窗口
        menuWindow.showAtLocation(EditUserDetailActivity.this.findViewById(R.id.tv_logout), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


    }


}
