package com.himeetu.ui.register;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.ui.login.LoginActivity;

/**
 * Created by object1984 on 15/12/2.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
        StatusBarCompat.compat(this, getResources().getColor(R.color.black));
        init();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

    }

    private void toRegister(){
        //Calls a new Activity
        startActivity(new Intent(this, CountyChooseActivity.class));

    }

    @Override
    public void onClick(View v) {
        toRegister();
    }


}
