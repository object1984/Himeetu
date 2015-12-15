package com.himeetu.ui.register;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;

/**
 * Created by object1984 on 15/12/14.
 */
public class InvitationCodeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT > 18) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        setContentView(R.layout.activity_invitation_code);

        init();
    }

}
