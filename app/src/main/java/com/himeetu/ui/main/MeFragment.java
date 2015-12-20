package com.himeetu.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.gson.Gson;
import com.himeetu.R;
import com.himeetu.adapter.MePagerAdapter;
import com.himeetu.app.Api;
import com.himeetu.event.UserInfoRefreshEvent;
import com.himeetu.model.GsonResult;
import com.himeetu.model.User;
import com.himeetu.model.UserImg;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.my.AttentionActivity;
import com.himeetu.ui.setup.EditUserDetailActivity;
import com.himeetu.ui.setup.SettingsActivity;
import com.himeetu.util.ToastUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.greenrobot.event.EventBus;


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

    private String path ;


    public static enum AttentionType{
        ATTENTION,FANS
    }
    private User user;
    private RoundedImageView head,country;



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

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me, container, false);

        init();
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
        head = (RoundedImageView) rootView.findViewById(R.id.riv_user_head);
        country = (RoundedImageView) rootView.findViewById(R.id.riv_user_country);

    }

    @Override
    protected void initViews() {
        super.initViews();

        ibtSetup.setOnClickListener(this);
        rlLogo.setOnClickListener(this);
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


        user = UserService.get();

        setUserData();

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


            case R.id.rl_logo:

                startActivity(new Intent(getActivity(), EditUserDetailActivity.class));

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(UserInfoRefreshEvent event){
        user = event.user;
        setUserData();
    }


    private void setSexImg(int sex){

        Drawable drawable= null;
        if(sex == 1){
            drawable= getResources().getDrawable(R.drawable.ic_female);
        }else{
            drawable= getResources().getDrawable(R.drawable.ic_male);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvUsername.setCompoundDrawables(null,null,drawable,null);
    }

    private void setUserData(){

        setSexImg(user.getSex());

        tvUsername.setText(user.getNickname());

        String path = UserService.getUserImgPath();

        String headPath = UserService.getUserHeadPath();

        Picasso.with(getActivity()).load(path+headPath).placeholder(R.drawable.image1).error(R.drawable.image1).into(head);

    }
}
