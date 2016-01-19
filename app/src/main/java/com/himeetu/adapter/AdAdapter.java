package com.himeetu.adapter;

import android.content.Context;
import android.support.v17.leanback.widget.GuidedActionEditText;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.himeetu.R;
import com.himeetu.app.Constants;
import com.himeetu.model.Ad;
import com.himeetu.util.LogUtil;
import com.himeetu.view.AutoScaleImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 16/1/19.
 */
public class AdAdapter extends PagerAdapter{
    private Context mContext;
    private List<View> viewList= new ArrayList<>();//把需要滑动的页卡添加到这个list中

    public AdAdapter(Context context) {
        mContext = context;
    }

    public void addAll(List<View> list){
        this.viewList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
        container.removeView(viewList.get(position));
    }
}
