package com.himeetu.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.himeetu.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 15/11/8.
 */
public class MainBottomBar extends LinearLayout implements View.OnClickListener {
    private List<ViewHolder> mViewHolderList;
    private int mCurrSelectedId;
    private OnTabSelectedListener mOnTabSelectedListener;
    private View homeView;
    private View favoriteView;
    private View cameraView;
    private View messageView;
    private View meView;

    public MainBottomBar(Context context) {
        this(context, null);
    }

    public MainBottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewHolderList = new ArrayList<>();

        LayoutInflater.from(context).inflate(R.layout.layout_main_bottom_bar, this, true);

        homeView = findViewById(R.id.bottom_bar_home);
        favoriteView = findViewById(R.id.bottom_bar_favorite);
        cameraView = findViewById(R.id.bottom_bar_camera);
        messageView = findViewById(R.id.bottom_bar_message);
        meView = findViewById(R.id.bottom_bar_me);

        homeView.setOnClickListener(this);
        favoriteView.setOnClickListener(this);
        cameraView.setOnClickListener(this);
        messageView.setOnClickListener(this);
        meView.setOnClickListener(this);

        ViewHolder homeHolder  = new ViewHolder();
        homeHolder.id = homeView.getId();
        homeHolder.tabIcon = (ImageView) findViewById(R.id.bottom_bar_home_icon);

        ViewHolder favoriteHolder  = new ViewHolder();
        favoriteHolder.id = favoriteView.getId();
        favoriteHolder.tabIcon = (ImageView) findViewById(R.id.bottom_bar_favorite_icon);

        ViewHolder cameraHolder  = new ViewHolder();
        cameraHolder.id = cameraView.getId();
        cameraHolder.tabIcon = (ImageView) findViewById(R.id.bottom_bar_camera_icon);

        ViewHolder messageHolder  = new ViewHolder();
        messageHolder.id = messageView.getId();
        messageHolder.tabIcon = (ImageView) findViewById(R.id.bottom_bar_message_icon);

        ViewHolder meHolder  = new ViewHolder();
        meHolder.id = meView.getId();
        meHolder.tabIcon = (ImageView) findViewById(R.id.bottom_bar_me_icon);


        mViewHolderList.add(homeHolder);
        mViewHolderList.add(favoriteHolder);
        mViewHolderList.add(cameraHolder);
        mViewHolderList.add(messageHolder);
        mViewHolderList.add(meHolder);

    }


    @Override
    public void onClick(View v) {
        setCurrSelectedByTag(v.getId());
        if (mOnTabSelectedListener != null) {
            mOnTabSelectedListener.onTabSelected(v.getId());
        }
    }

    public void setCurrSelectedByTag(int id) {
        if (mCurrSelectedId == id) {
            return;
        }

        mCurrSelectedId = id;

        for (ViewHolder holder : mViewHolderList) {
            if (mCurrSelectedId == holder.id) {
                holder.tabIcon.setSelected(true);
            } else  {
                holder.tabIcon.setSelected(false);
            }
        }

    }


    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        mOnTabSelectedListener = onTabSelectedListener;
    }

    private static class ViewHolder {
        public int id;
        public TabParam pageParam;
        public ImageView tabIcon;
        public TextView tabTitle;
    }


    public static class TabParam {
        public int backgroundColor = android.R.color.white;
        public int iconResId;
        public int tabViewResId;
        public String title;
    }


    public interface OnTabSelectedListener {
        void onTabSelected(int id);
    }
}
