package com.himeetu.ui.setup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;

/**
 * 密码修改
 */
public class EditPassWordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_word);

        initToolBar();

    }

    private void initToolBar() {
        setupToolbar(true,R.string.set_password);
        setToolBarColor(getResources().getColor(R.color.white));
        setRightTextAndVisible(R.string.commit, View.VISIBLE);
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交
            }
        });
    }
}
