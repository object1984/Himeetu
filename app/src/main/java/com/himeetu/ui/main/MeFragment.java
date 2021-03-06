package com.himeetu.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ViewUtils;
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
import com.himeetu.BuildConfig;
import com.himeetu.R;
import com.himeetu.adapter.MePagerAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.app.NavHelper;
import com.himeetu.event.UserInfoRefreshEvent;
import com.himeetu.model.GsonResult;
import com.himeetu.model.User;
import com.himeetu.model.UserImg;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseFragment;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.base.BaseVolleyFragment;
import com.himeetu.ui.my.AttentionActivity;
import com.himeetu.ui.setup.EditUserDetailActivity;
import com.himeetu.ui.setup.SettingsActivity;
import com.himeetu.util.DensityUtil;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.RoundedTransformation;
import com.himeetu.util.ToastUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.List;

import de.greenrobot.event.EventBus;


public class MeFragment extends BaseVolleyFragment implements View.OnClickListener {

    private static final String ARG_TYPE= "ARG_TYPE";

    public static final String TYPE_USER_SELF = "TYPE_USER_SELF";
    public static final  String TYPE_USER_OTHER = "TYPE_USER_OTHER";
    public   String userType;
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private MePagerAdapter pagerAdapter;
    private List<Integer> imageIdList;
    //    private ImageButton ibtBack;
    private ImageButton ibtSetup;
    private RelativeLayout rlLogo;
    private TextView tvUsername;
    private TextView tvAttention;
    private TextView tvFans;

    public static final String TAG_GET_NUM = "TAG_GET_NUM";
    public static final String TAG_GET_USER_DATA = "TAG_GET_USER_DATA";
    private String path;
    private String uid;
    private RelativeLayout rlUser;
    private TextView tvAtten;


    public static enum AttentionType {
        ATTENTION, FANS
    }

    private User user;
    private RoundedImageView head, country;


    public MeFragment() {
        // Required empty public constructor
    }


    public static MeFragment newInstance(String userType,  String uid) {
        MeFragment fragment = new MeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, userType);
        args.putString("uid", uid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userType = getArguments().getString(ARG_TYPE);
            uid = getArguments().getString("uid");
        }else{
            userType = TextUtils.isEmpty(uid)?TYPE_USER_SELF:TYPE_USER_OTHER;
        }

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
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
        tvFans = (TextView) rootView.findViewById(R.id.tv_fans);
        head = (RoundedImageView) rootView.findViewById(R.id.riv_user_head);
        country = (RoundedImageView) rootView.findViewById(R.id.riv_user_country);
        rlUser = (RelativeLayout) rootView.findViewById(R.id.rl_user);
        tvAtten = (TextView) rootView.findViewById(R.id.tv_atten);
    }

    @Override
    protected void initViews() {
        super.initViews();

        ibtSetup.setOnClickListener(this);
        rlLogo.setOnClickListener(this);
//        tvUsername.setOnClickListener(this);
        tvAttention.setOnClickListener(this);
        tvFans.setOnClickListener(this);
        tvAtten.setOnClickListener(this);

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

        initData();
    }

    /**
     * 判断是自己还是其他人
     */
    private void initData() {
        if (!TextUtils.isEmpty(uid)) {

            getUserData(uid);

            otherUserView(false);

        } else {
            user = UserService.get();

            setUserData();

            otherUserView(true);
        }
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

        initData();
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

                NavHelper.toSettingsActivity(getActivity());

                break;
            case R.id.tv_attention:  //关注

                NavHelper.toAttentionActivity(getActivity(), user.getUid() + "", AttentionType.ATTENTION);
                break;
            case R.id.tv_fans:   //粉丝

                NavHelper.toAttentionActivity(getActivity(), user.getUid() + "", AttentionType.FANS);

                break;


            case R.id.rl_logo:
                if(userType.equals(TYPE_USER_SELF)){
                    NavHelper.toEditUserDetailActivity(getActivity());
                }
                break;

            case R.id.tv_atten: //关注别人

                Api.addFriends(AttentionActivity.TAG_ADD_FRIEND, uid, this, this);

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(UserInfoRefreshEvent event) {
        user = event.user;
        setUserData();
    }


    private void setSexImg(int sex) {

        Drawable drawable = null;
        if (sex == 1) {
            drawable = getResources().getDrawable(R.drawable.ic_female);
        } else {
            drawable = getResources().getDrawable(R.drawable.ic_male);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tvUsername.setCompoundDrawables(null, null, drawable, null);
    }

    private void setUserData() {

        setSexImg(user.getSex());

        getNum(user.getUid() + "");

        tvUsername.setText(user.getNickname());

        Picasso.with(getActivity()).load(Constants.HEAD_IMG_BASE+user.getPortrait()).placeholder(R.drawable.img_avatar_default)
                .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(head);

    }

    private void getNum(String uid) {
        Api.getNum(TAG_GET_NUM, uid, this, this);

    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if (TAG_GET_NUM.equals(tag)) {


            if (response == null) {
                return;
            }

            if (response.getCode() == 0) {
                JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());
                String friends_num = JsonUtil.getString(jsonObject, "friend_num");
                String fans_num = JsonUtil.getString(jsonObject, "fans_num");

                tvAttention.setText(String.format("%s %s", getString(R.string.user_my_follow), friends_num));
                tvFans.setText(String.format("%s %s", getString(R.string.user_my_followers), fans_num));

            } else {
                ToastUtil.show(response.getMsg());
            }


        } else if (TAG_GET_USER_DATA.equals(tag)) {

            if (response.getCode() == 0) {
                user = new Gson().fromJson(response.getJsonStr(), User.class);
                setUserData();
            } else {

                ToastUtil.show(response.getMsg());
            }

        } else if (AttentionActivity.TAG_ADD_FRIEND.equals(tag)) {

            if (response.getCode() == 0) {
                ToastUtil.show(R.string.success);
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
    public void onResume() {
        super.onResume();

        if(user != null){
            getNum(user.getUid() + "");
            Picasso.with(getActivity()).load(Constants.HEAD_IMG_BASE+user.getPortrait()).placeholder(R.drawable.img_avatar_default)
                    .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(head);
        }

    }

    /**
     * 获取其他用户的数据
     */
    private void getUserData(String uid) {
        Api.getUserData(TAG_GET_USER_DATA, uid, this, this);
    }


    private void otherUserView(boolean isSelf) {
        ViewGroup.LayoutParams pm = rlUser.getLayoutParams();
        if (isSelf) {
            pm.height = DensityUtil.dip2px(getActivity(), 200);
            tvAtten.setVisibility(View.GONE);
            ibtSetup.setVisibility(View.VISIBLE);
        } else {
            pm.height = DensityUtil.dip2px(getActivity(), 250);
            tvAtten.setVisibility(View.VISIBLE);
            ibtSetup.setVisibility(View.GONE);
        }
        rlUser.setLayoutParams(pm);
    }
}
