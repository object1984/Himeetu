package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.himeetu.R;
import com.himeetu.model.User;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.SelectPicPopupWindow;
import android.os.Bundle;
import android.app.Activity;
import android.widget.RelativeLayout;
import com.github.siyamed.shapeimageview.RoundedImageView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 编辑用户资料   id   email  不能修改
 */
public class EditUserDetailActivity extends BaseActivity implements View.OnClickListener {
    private SelectPicPopupWindow menuWindow;
    private RelativeLayout rlUserHead;
    private RoundedImageView rivUserHead ,riv_user_head_edit;
    private RelativeLayout rlName;
    private TextView tvName ;
    private RelativeLayout rlPhone;
    private TextView tvPhone;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlBirthday;
    private TextView tvBirthday;
    private RelativeLayout rlQuestion;
    private TextView tvQuestion;
    private TextView tvAnswer;
    private LinearLayout llShow,llEdit;
    private EditText et_name,et_email,et_phone,et_sex,et_birthday,et_question,et_answer,et_id;
    private TextView tv_head_edit;



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

        rlUserHead = (RelativeLayout) findViewById(R.id.rl_user_head);
        rivUserHead = (RoundedImageView) findViewById(R.id.riv_user_head);
        rlName = (RelativeLayout) findViewById(R.id.rl_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        rlBirthday = (RelativeLayout) findViewById(R.id.rl_birthday);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        rlQuestion = (RelativeLayout) findViewById(R.id.rl_question);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        tvAnswer = (TextView) findViewById(R.id.tv_answer);
        riv_user_head_edit = (RoundedImageView) findViewById(R.id.riv_user_head_edit);
        llShow = (LinearLayout) findViewById(R.id.ll_show);
        llEdit= (LinearLayout) findViewById(R.id.ll_edit);
        tv_head_edit = (TextView) findViewById(R.id.tv_head_edit);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_birthday = (EditText) findViewById(R.id.et_birthday);
        et_question = (EditText) findViewById(R.id.et_question);
        et_answer = (EditText) findViewById(R.id.et_answer);
        et_id = (EditText) findViewById(R.id.et_id);

    }

    @Override
    protected void initViews() {
        super.initViews();

        User user = UserService.get();

        et_id.setText(user.getUid()+"");
        et_name.setText(user.getNickname());
        et_birthday.setText(user.getBirthday());
        et_sex.setText("1".equals(user.getSex())?"女":"男");
//        et_email.setText(user.get);

    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

//        rlUserHead.setOnClickListener(this);
//        rlName.setOnClickListener(this);
//        rlPhone.setOnClickListener(this);
//        rlSex.setOnClickListener(this);
//        rlBirthday.setOnClickListener(this);
//        rlQuestion.setOnClickListener(this);
        tv_head_edit.setOnClickListener(this);
        riv_user_head_edit.setOnClickListener(this);


    }

    private void initToolBar() {
        setupToolbar(true, R.string.edit_user_data);
        setRightTextAndVisible(R.string.complete, View.VISIBLE);

        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text =getRightText();

                if(getString(R.string.edit).equals(text)){//完成

                    setRightTextAndVisible(R.string.edit, View.VISIBLE);
                    llShow.setVisibility(View.GONE);
                    llEdit.setVisibility(View.VISIBLE);
                }else{//编辑
                    setRightTextAndVisible(R.string.complete, View.VISIBLE);
                    llShow.setVisibility(View.VISIBLE);
                    llEdit.setVisibility(View.GONE);


                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.rl_user_head:
//
//                break;
//
//            case R.id.rl_name:
//
//                break;
//            case R.id.rl_phone:
//
//                break;
//            case R.id.rl_sex:
//
//                break;
//            case R.id.rl_birthday:
//
//                break;
//            case R.id.rl_question:
//
//                break;


            case R.id.riv_user_head_edit:
                showSelectHeadView();
                break;

            case R.id.tv_head_edit:
                showSelectHeadView();

                break;

        }

    }


    /**
     * 显示pop
     */
    private void showSelectHeadView() {

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
        menuWindow.showAtLocation(EditUserDetailActivity.this.findViewById(R.id.tv_head_edit), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


    }

    private void buildData(){

        if(isNull(et_name) || isNull(et_phone) || isNull(et_sex) || isNull(et_birthday) || isNull(et_question) || isNull(et_answer)){

//            ToastUtil.show();


        }

    }

    private boolean isNull(EditText et){
        if(et == null || isNull(et.getText().toString())){
            return  true;
        }else {
            return  false;
        }
    }

    private boolean isNull(String str){
        if(str == null || str.trim().length() < 1){
            return true;
        }else{
            return false;
        }
    }


}
