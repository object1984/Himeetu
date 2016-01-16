package com.himeetu.ui.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiActivity;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.util.DateUtils;
import com.himeetu.util.JsonUtil;
import com.himeetu.view.ClipViewPager;
import com.himeetu.view.ScalePageTransformer;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FavoriteFragment extends BaseVolleyFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = FavoriteFragment.class.getCanonicalName();
    private static final String TAG_API_GET_ACTIVITY_ON_GOING = "TAG_API_GET_ACTIVITY_ON_GOING";
    private static final String TAG_API_GET_ACTIVITY_END = "TAG_API_GET_ACTIVITY_END";

    private ListView mEndActivityListView;
    private TextView mCurrentSelectedHiActivityNameTextView;
    private QuickAdapter<HiActivity> mHiActivityListAdapter;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            setStatusBarColor(R.color.black);
        }
    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        setStatusBarColor(R.color.black);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        init();
        getOnGoingActivity();
        geEndActivity();
        return rootView;
    }

    private ClipViewPager mViewPager;
    private HIActivityAdapter mHiActivityAdapter;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NavHelper.toActivityTalksPage(getActivity());
    }

    public  class HIActivityAdapter extends PagerAdapter {

        private final List<HiActivity> mList;
        private final Context mContext;

        public HIActivityAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public HiActivity getItem(int position){
            return mList.get(position);
        }

        public void addAll(List<HiActivity> list) {
            mList.addAll(list);
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            ImageView imageView = null;
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHelper.toActivityDetailPage(getActivity(), getItem(position));
                }
            });

            Picasso.with(mContext).load(mList.get(position).getImgPath()).placeholder(R.drawable.img_default)
                    .error(R.drawable.img_default).into(imageView);

            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }
    @Override
    protected void loadViews() {
        super.loadViews();



        mEndActivityListView = (ListView)rootView.findViewById(R.id.list_favorite);
        mHiActivityListAdapter = new QuickAdapter<HiActivity>(getActivity(), R.layout.item_list_activity) {
            @Override
            protected void convert(BaseAdapterHelper helper, HiActivity item) {
                helper.setText(R.id.text_name, item.getName());
                helper.setText(R.id.text_address, item.getAddress());

                Calendar cal = Calendar.getInstance();
                cal.setTime(item.getStartDate());
                String time = "";
                if (cal.get(Calendar.AM_PM) == Calendar.PM) {
                    time += "PM: ";
                }else {
                    time += "AM: ";
                }

                time += String.format("%s  %s", DateUtils.formatTime(item.getStartDate()), DateUtils.formatYear(item.getStartDate()));

                helper.setText(R.id.text_time, time);

                Picasso.with(getActivity()).load( item.getImgPath()).placeholder(R.drawable.img_default).error(R.drawable.img_default).into((ImageView) helper.getView().findViewById(R.id.img_img));
            }
        };

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View headerView = inflater.inflate(R.layout.header_list_favorite, mEndActivityListView, false);
        mCurrentSelectedHiActivityNameTextView = (TextView)headerView.findViewById(R.id.text_name);
        mEndActivityListView.addHeaderView(headerView);
        mEndActivityListView.setAdapter(mHiActivityListAdapter);
        mEndActivityListView.setOnItemClickListener(this);

        mViewPager = (ClipViewPager)headerView. findViewById(R.id.viewpager);
        mViewPager.setPageTransformer(true, new ScalePageTransformer());

        rootView.findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        mHiActivityAdapter = new HIActivityAdapter(getActivity());
        mViewPager.setAdapter(mHiActivityAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                HiActivity selectedHiActivity = mHiActivityAdapter.getItem(position);

                if(selectedHiActivity != null){
                    mCurrentSelectedHiActivityNameTextView.setText(selectedHiActivity.getName());
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){

        }
    }

    private void getOnGoingActivity(){
        //暂时只获取前10个，以后根据业务修改
        Api.getOnGoingActivity(TAG_API_GET_ACTIVITY_END, 0, 10, this, this);
    }

    private int pageNum = 0;
    private int pageSize = 20;
    private void geEndActivity(){
        Api.getOnGoingActivity(TAG_API_GET_ACTIVITY_ON_GOING, pageNum*pageSize, pageSize, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_GET_ACTIVITY_ON_GOING.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = gsonBuilder.create();

            Type listType = new TypeToken<List<HiActivity>>() {}.getType();
            List<HiActivity> hiActivities  = gson.fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(hiActivities != null){
                mHiActivityAdapter.addAll(hiActivities);

                if (hiActivities.size() > 2){
                    mViewPager.setCurrentItem(1);
                }else if(hiActivities.size() == 1){
                    mViewPager.setCurrentItem(0);
                }else if(hiActivities.size() == 2){
                    mViewPager.setCurrentItem(1);
                }

                HiActivity currentSelectedHiActivity = mHiActivityAdapter.getItem(mViewPager.getCurrentItem());
                mCurrentSelectedHiActivityNameTextView.setText(currentSelectedHiActivity.getName());

            }
        }

        if(TAG_API_GET_ACTIVITY_END.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = gsonBuilder.create();

            Type listType = new TypeToken<List<HiActivity>>() {}.getType();
            List<HiActivity> hiActivities  = gson.fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(hiActivities != null){
                mHiActivityListAdapter.addAll(hiActivities);
                mHiActivityListAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
