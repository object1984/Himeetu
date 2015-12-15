package com.himeetu.ui.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.adapter.RecyclingPagerAdapter;
import com.himeetu.model.HiEvent;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.base.StatusBarCompat;
import com.himeetu.util.ToolbarColorizeHelper;
import com.himeetu.view.ClipViewPager;
import com.himeetu.view.ScalePageTransformer;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends BaseFragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private List<Integer> imageIdList;
    private ListView mFavoriteListView;
    private QuickAdapter<HiEvent> mFravoriteAdapter;
    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.black));
            }
            StatusBarCompat.compat(getActivity(), getResources().getColor(R.color.black));
        }
    }

    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
            if (Build.VERSION.SDK_INT >= 21) {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.black));
            }
        StatusBarCompat.compat(getActivity(), getResources().getColor(R.color.black));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        init();
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setTitle("养车钱包");
        return rootView;
    }

    private ClipViewPager mViewPager;
    private TubatuAdapter mPagerAdapter;



    private void initData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);
        list.add(R.drawable.image1);

        //设置OffscreenPageLimit
        mViewPager.setOffscreenPageLimit(2);

        mPagerAdapter.addAll(list);
        mViewPager.setCurrentItem(1);
    }

    public static class TubatuAdapter extends PagerAdapter {

        private final List<Integer> mList;
        private final Context mContext;

        public TubatuAdapter(Context context) {
            mList = new ArrayList<>();
            mContext = context;
        }

        public void addAll(List<Integer> list) {
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
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = null;
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setTag(position);
            imageView.setImageResource(mList.get(position));
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



        mFavoriteListView = (ListView)rootView.findViewById(R.id.list_favorite);
        mFravoriteAdapter = new QuickAdapter<HiEvent>(getActivity(), R.layout.item_list_favorite) {
            @Override
            protected void convert(BaseAdapterHelper helper, HiEvent item) {

            }
        };

        LayoutInflater inflater = LayoutInflater.from(getActivity());

        View headerView = inflater.inflate(R.layout.header_list_favorite, mFavoriteListView, false);
        mFavoriteListView.addHeaderView(headerView);
        mFavoriteListView.setAdapter(mFravoriteAdapter);
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());
        mFravoriteAdapter.add(new HiEvent());

        mViewPager = (ClipViewPager)headerView. findViewById(R.id.viewpager);
        mViewPager.setPageTransformer(true, new ScalePageTransformer());

        rootView.findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        mPagerAdapter = new TubatuAdapter(getActivity());
        mViewPager.setAdapter(mPagerAdapter);

        initData();
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


}
