package com.himeetu.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.event.UserInfoRefreshEvent;
import com.himeetu.model.GsonResult;
import com.himeetu.model.ListItem;
import com.himeetu.model.User;
import com.himeetu.model.UserImg;
import com.himeetu.model.service.UserService;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.login.LoginActivity;
import com.himeetu.ui.my.ActivitysFragment;
import com.himeetu.ui.my.ActivitysFragment.OnListFragmentInteractionListener;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.MainBottomBar;

import org.json.JSONException;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

public class MainActivity extends BaseVolleyActivity implements MainBottomBar.OnTabSelectedListener, OnListFragmentInteractionListener {
    private static final String TAG = "MainActivity";

    private static final String SAVE_INSTANCE_CURRENT_TAG = "currentTag";
    private static final String TAG_API_GET_SELF_INFO = "TAG_API_GET_SELF_INFO";
    private final String GET_IMG_PATH_TAG = "TAG_API_GET_IMG_PATH";
    private final String GET_HEAD_IMG_PATH_TAG = "TAG_API_GET_HEAD_IMG_PATH";


    private static final int TAB_PAGE_HOME = R.id.bottom_bar_home;
    private static final int TAB_PAGE_FAVORITE = R.id.bottom_bar_favorite;
    private static final int TAB_PAGE_MESSAGE = R.id.bottom_bar_message;
    private static final int TAB_PAGE_ME = R.id.bottom_bar_me;
    private static final int TAB_PAGE_PHOTO = R.id.bottom_bar_camera;
    private int mCurrentTag = -1;
    private MainBottomBar mMainBottomBar;
    private User user;
    private String userHeadPath;
    protected boolean isLogin = true;
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

        EventBus.getDefault().register(this);

        getSelfInfo();
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
        if (tag == TAB_PAGE_PHOTO) {
            NavHelper.toPhotoMainPage(this);
        } else {
            showFragment(tag);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_INSTANCE_CURRENT_TAG, mCurrentTag);
    }

    @Override
    public void onListFragmentInteraction(ListItem item) {

    }


    private void getSelfInfo() {
        Api.getSelfInfo(TAG_API_GET_SELF_INFO, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(!isLogin){
            return;
        }
        int code = response.getCode();
        //Result： 0 表示成功，1 参数错误，2 未登录，3 权限不够，4 重复操作
        switch (code){
            case 0:
                break;
            case 1:
                ToastUtil.show("参数错误");
                break;
            case 2:
                isLogin = false;
                UserService.logout();
                ToastUtil.show("未登录");
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return;
            case 3:
                ToastUtil.show("权限不够");
                break;
            case 4:
                ToastUtil.show("重复操作");
                break;
        }

        if (TAG_API_GET_SELF_INFO.equals(tag)) {

            user = new Gson().fromJson(response.getJsonStr(), User.class);

            if (user != null) {
                user.setUsername(getIntent().getStringExtra(Argument.USERNAME));
                EventBus.getDefault().post(new UserInfoRefreshEvent(user));
                UserService.save(user);
            }

        } else if (GET_IMG_PATH_TAG.equals(tag)) {

            UserImg userImg = new Gson().fromJson(response.getJsonStr(), UserImg.class);

            if (userImg.getCount() == 1) {

                userHeadPath = userImg.getPaths().getPath();

                if (!TextUtils.isEmpty(userHeadPath)) {
                    UserService.saveUserImgPath(userHeadPath);
                }

                getUserHeadImg(user.getUid() + "");

            }
        } else if (GET_HEAD_IMG_PATH_TAG.equals(tag)) {

            if (response.getCode() == 0) {
                JSONObject json = null;
                try {
                    json = new JSONObject(response.getJsonStr());
                    String img = json.getString("portrait");

                    if (!TextUtils.isEmpty(img)) {
                        UserService.saveUserHeadPath(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                ToastUtil.show(response.getMsg());
            }

        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(UserInfoRefreshEvent event) {
        user = event.user;

        getUserImgPath();  //获取到用户数据之后 通过uid 获取 用户图片路径 和 用户头像数据
    }

    /**
     * 获取用户图片url前缀
     */
    public void getUserImgPath() {

        Api.getUserImgPath(GET_IMG_PATH_TAG, user.getUid() + "", this, this);

    }


    /**
     * 获取用户头像
     */
    public void getUserHeadImg(String uid) {

        Api.getUserHeadImgPath(GET_HEAD_IMG_PATH_TAG, uid, this, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 100){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

            finish();
        }
    }

    //上次按下返回键的系统时间
    private long lastBackTime = 0;
    //当前按下返回键的系统时间
    private long currentBackTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //捕获返回键按下的事件
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //获取当前系统时间的毫秒数
            currentBackTime = System.currentTimeMillis();
            //比较上次按下返回键和当前按下返回键的时间差，如果大于2秒，则提示再按一次退出
            if(currentBackTime - lastBackTime > 2 * 1000){
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                lastBackTime = currentBackTime;
            }else{ //如果两次按下的时间差小于2秒，则退出程序
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
