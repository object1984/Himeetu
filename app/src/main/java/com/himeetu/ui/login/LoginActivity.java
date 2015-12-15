package com.himeetu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.register.CountyChooseActivity;
import com.himeetu.ui.register.InvitationCodeActivity;
import com.himeetu.ui.register.RegisterActivity;

/**
 * Created by object1984 on 15/12/2.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         if (android.os.Build.VERSION.SDK_INT > 18) {
             Window window = getWindow();
             window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
         }

        setContentView(R.layout.activity_login);

        init();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.text_register).setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_register:
                toRegister();
                break;
            case R.id.btn_login:
                toLogin();
                break;
        }
    }

    private void toLogin() {
        NavHelper.toMainPage(this);
    }



    private void toRegister(){
        startActivity(new Intent(this, InvitationCodeActivity.class));
    }
}
