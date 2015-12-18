package com.himeetu.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.adapter.MePagerAdapter;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.my.AttentionActivity;
import com.himeetu.ui.setup.SettingsActivity;

import java.util.List;


public class MeFragment extends BaseFragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private MePagerAdapter pagerAdapter;
    private List<Integer> imageIdList;
//    private ImageButton ibtBack;
    private ImageButton ibtSetup;
    private RelativeLayout rlLogo;
    private TextView tvUsername;
    private TextView tvAttention;
    private View tvFans;
    public static final  String TYPE = "type";
    public static enum AttentionType{
        ATTENTION,FANS
    }


    public MeFragment() {
        // Required empty public constructor
    }

    public static MeFragment newInstance(String param1, String param2) {
        MeFragment fragment = new MeFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me, container, false);

        init();
//        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
//        setTitle("养车钱包");
        return rootView;
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        radioGroup = (RadioGroup) rootView.findViewById(R.id.custom_radiogroup);
        viewPager = (ViewPager) rootView.findViewById(R.id.customer_viewPager);
        ibtSetup = (ImageButton) rootView.findViewById(R.id.ibt_setup);
        rlLogo = (RelativeLayout) rootView.findViewById(R.id.rl_logo);
        tvUsername = (TextView) rootView.findViewById(R.id.tv_username);
        tvAttention = (TextView) rootView.findViewById(R.id.tv_attention);
        tvFans = rootView.findViewById(R.id.tv_fans);


    }

    @Override
    protected void initViews() {
        super.initViews();

        ibtSetup.setOnClickListener(this);
//        rlLogo.setOnClickListener(this);
//        tvUsername.setOnClickListener(this);
        tvAttention.setOnClickListener(this);
        tvFans.setOnClickListener(this);

        pagerAdapter = new MePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        radioGroup.check(R.id.tab1);
        viewPager.setCurrentItem(0);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.tab2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.tab3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                switch (arg0) {
                    case 0:
                        radioGroup.check(R.id.tab1);
                        break;
                    case 1:
                        radioGroup.check(R.id.tab2);
                        break;
                    case 2:
                        radioGroup.check(R.id.tab3);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });


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

        switch (v.getId()) {
            case R.id.ibt_setup: //设置

                startActivity(new Intent(getActivity(), SettingsActivity.class));


                break;
            case R.id.tv_attention:  //关注

                Intent intent = new Intent(getActivity(), AttentionActivity.class);
                intent.putExtra("id","");
                intent.putExtra(TYPE,AttentionType.ATTENTION);
                startActivity(intent);

                break;
            case R.id.tv_fans:   //粉丝

                intent = new Intent(getActivity(), AttentionActivity.class);
                intent.putExtra("id","");
                intent.putExtra(TYPE,AttentionType.FANS);
                startActivity(intent);
                break;

        }
    }


}
