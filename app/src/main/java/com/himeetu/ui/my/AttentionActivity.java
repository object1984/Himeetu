package com.himeetu.ui.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.main.MeFragment;

/**
 * 关注/粉丝 页面
 */
public class AttentionActivity extends BaseActivity {
    private MeFragment.AttentionType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        type = (MeFragment.AttentionType) getIntent().getSerializableExtra(MeFragment.TYPE);

        initToolBar();

    }


    private void initToolBar() {
        int title = 0;
        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
            title = R.string.consider_me;
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
            title = R.string.me_consider;
        }

        setupToolbar(true,title);
        setToolBarColor(getResources().getColor(R.color.white));
        setRightTextAndVisible(0, View.INVISIBLE);
    }

}
