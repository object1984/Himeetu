package com.himeetu.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import com.himeetu.R;
import com.himeetu.model.PersonState;
import com.himeetu.ui.my.ActivitysFragment;

/**
 * 功能说明: 我的页面适配器
 * 日期:	2015-12-14
 * <p>
 * 历史记录
 * 修改内容：
 * 修改人员：  zhihong lan
 * 修改日期： 2015-12-14
 */
public class MePagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list = null;

    /**
     * 构造方法
     *
     * @param fm
     */
    public MePagerAdapter(FragmentManager fm) {

        super(fm);

        list = new ArrayList<Fragment>();

        list.add(ActivitysFragment.newInstance(1));
        list.add(ActivitysFragment.newInstance(1));
        list.add(ActivitysFragment.newInstance(2));
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

}
