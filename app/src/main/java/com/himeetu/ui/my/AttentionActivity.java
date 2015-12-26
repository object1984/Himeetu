package com.himeetu.ui.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.himeetu.BuildConfig;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.model.PersonState;
import com.himeetu.model.UserImg;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.main.MeFragment;
import com.himeetu.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关注/粉丝 页面    获取好友列表 ＝＝ 我的关注
 * 个人动态  11  获取全部好友最新发表的图片
 */
public class AttentionActivity extends BaseVolleyActivity {
    private MeFragment.AttentionType type;
    private ListView mListView;
    private List<PersonState> lists;
    private Map<Integer, Boolean> flagMap;
    private final String TAG_GET_FRIENDS_LIST = "TAG_GET_FRIENDS_LIST";
    public static final String TAG_ADD_FRIEND = "TAG_ADD_FRIEND";
    private final String TAG_DEL_FRIEND = "TAG_DEL_FRIEND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        type = (MeFragment.AttentionType) getIntent().getSerializableExtra(MeFragment.TYPE);

        initToolBar();

        super.init();

        initData();

    }


    @Override
    protected void loadViews() {
        super.loadViews();

        mListView = (ListView) findViewById(R.id.listView);


    }

    @Override
    protected void initViews() {
        super.initViews();

        lists = new ArrayList<>();
        flagMap = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            lists.add(new PersonState());
        }

        mListView.setAdapter(new QuickAdapter<PersonState>(AttentionActivity.this, R.layout.item_list_attention, lists) {
            @Override
            protected void convert(final BaseAdapterHelper helper, PersonState item) {

                helper.setText(R.id.tv_name, "test");

                helper.setImageResource(R.id.im_head, R.drawable.image1);

                int position = helper.getPosition();

                boolean isChecked = false;

                if (flagMap.containsKey(position)) {

                    isChecked = flagMap.get(position);

                }

                helper.setChecked(R.id.bt_attention, isChecked);

                helper.setOnCheckedListener(R.id.bt_attention, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        flagMap.put(helper.getPosition(), isChecked);
                    }
                });


            }
        });

    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

//        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
//
//
//
//
//
//
//        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {  //我的关注
//
//            if (BuildConfig.DEBUG) Log.d("我的关注＝＝＝", response.getJsonStr());
//        }


        if (AttentionActivity.TAG_ADD_FRIEND.equals(tag)) {

            try {
                JSONObject json = new JSONObject(response.getJsonStr());
                if ("0".equals(json.getString("result"))) {

                    ToastUtil.show(R.string.success);
                } else {
                    ToastUtil.show(json.getString("msg"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else {

        }

    }

    private void initToolBar() {
        int title = 0;
        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
            title = R.string.consider_me;
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
            title = R.string.me_consider;
        }

        setupToolbar(true, title);
        setToolBarColor(getResources().getColor(R.color.white));
    }

    private void initData() {

        if (type == MeFragment.AttentionType.ATTENTION.FANS) {


        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {

            getFriendsList();
        }

    }


    private void getFriendsList() {

        Api.getFriendsList(TAG_GET_FRIENDS_LIST, this, this);

    }

    private void addFriend(String friendId) {

        Api.addFriends(TAG_ADD_FRIEND, friendId, this, this);
    }

    private void delFriend(String friendId) {
        Api.delFriends(TAG_DEL_FRIEND, friendId, this, this);
    }

}
