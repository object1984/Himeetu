package com.himeetu.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.view.MainBottomBar;

public class MainActivity extends BaseActivity implements MainBottomBar.OnTabSelectedListener {
    private static final String TAG = "MainActivity";

    private static final String SAVE_INSTANCE_CURRENT_TAG = "currentTag";

    private static final int TAB_PAGE_HOME = R.id.bottom_bar_home;
    private static final int TAB_PAGE_FAVORITE = R.id.bottom_bar_favorite;
    private static final int TAB_PAGE_MESSAGE = R.id.bottom_bar_message;
    private static final int TAB_PAGE_ME = R.id.bottom_bar_me;
    private int mCurrentTag = -1;
    private MainBottomBar mMainBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        init();

        // 默认显示首页
        int defaultTag = TAB_PAGE_HOME;
        if (savedInstanceState != null) {
            defaultTag = savedInstanceState.getInt(SAVE_INSTANCE_CURRENT_TAG, defaultTag);
        }
        showFragment(defaultTag);
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        mMainBottomBar = (MainBottomBar) findViewById(R.id.main_bottom_bar);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        mMainBottomBar.setOnTabSelectedListener(this);
    }

    /**
     * 显示 tag 对应的 fragment
     *
     * @param tag 要显示的 fragment 对应的 tag
     */
    private void showFragment(int tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isFragmentShown(transaction, tag)) {
            return;
        }
        mMainBottomBar.setCurrSelectedByTag(tag);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(tag));
        if (fragment == null) {
            fragment = getFragmentInstance(tag);
            transaction.add(R.id.main_container, fragment, String.valueOf(tag));
        } else {
            transaction.show(fragment);
        }

        transaction.commit();
    }

    private void hideAllFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(TAB_PAGE_HOME));
        if (homeFragment != null && !homeFragment.isHidden()) {
            transaction.hide(homeFragment);
        }

        Fragment favoriteFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(TAB_PAGE_FAVORITE));
        if (favoriteFragment != null && !favoriteFragment.isHidden()) {
            transaction.hide(favoriteFragment);
        }

        Fragment messageFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(TAB_PAGE_MESSAGE));
        if (messageFragment != null && !messageFragment.isHidden()) {
            transaction.hide(messageFragment);
        }

        Fragment meFragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(TAB_PAGE_ME));
        if (meFragment != null && !meFragment.isHidden()) {
            transaction.hide(meFragment);
        }

        transaction.commit();
    }


    /**
     * 判断要显示的fragment是否已经处于显示状态，不是的话会将之前的fragment隐藏
     *
     * @param transaction
     * @param newTag      要显示的fragment的标签
     * @return 已显示返回true, 否则返回false
     */
    private boolean isFragmentShown(FragmentTransaction transaction, int newTag) {
        if (newTag == mCurrentTag) {
            return true;
        }

        if (mCurrentTag == -1) {
            mCurrentTag = newTag;
            return false;
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(String.valueOf(mCurrentTag));
        if (fragment != null && !fragment.isHidden()) {
            transaction.hide(fragment);
        }

        mCurrentTag = newTag;

        return false;
    }

    /**
     * 根据tag得到fragment实例
     *
     * @param tag fragment对于标签
     * @return
     */
    public Fragment getFragmentInstance(int tag) {
        Fragment fragment = null;

        switch (tag) {
            case TAB_PAGE_HOME:
                fragment = new HomeFragment();
                break;
            case TAB_PAGE_FAVORITE:
                fragment = new FavoriteFragment();
                break;
            case TAB_PAGE_MESSAGE:
                fragment = new MessageFragment();
                break;
            case TAB_PAGE_ME:
                fragment = new MeFragment();
                break;
        }

        return fragment;
    }


    @Override
    public void onTabSelected(int tag) {
        showFragment(tag);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_INSTANCE_CURRENT_TAG, mCurrentTag);
    }
}
