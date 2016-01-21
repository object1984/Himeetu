package com.himeetu.ui.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.himeetu.BuildConfig;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.model.Friend;
import com.himeetu.model.GsonResult;
import com.himeetu.model.User;
import com.himeetu.model.UserImg;
import com.himeetu.model.service.UserService;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.main.MeFragment;
import com.himeetu.util.RoundedTransformation;
import com.himeetu.util.ToastUtil;
import com.squareup.picasso.Picasso;

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
    private final String TAG_GET_FRIENDS_LIST = "TAG_GET_FRIENDS_LIST";
    public static final String TAG_ADD_FRIEND = "TAG_ADD_FRIEND";
    private final String TAG_DEL_FRIEND = "TAG_DEL_FRIEND";
    private final String TAG_GET_FANS_LIST = "TAG_GET_FANS_LIST";
    private QuickAdapter adapter;
    private List<Friend.list> friends;
    private int start = 0;
    private int limit = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        type = (MeFragment.AttentionType) getIntent().getSerializableExtra(MeFragment.TYPE);

        initToolBar();

        init();

        initData();

        addFriend("12");
//        addFriend("14");
//        addFriend("15");

    }


    @Override
    protected void loadViews() {
        super.loadViews();
        mListView = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void initViews() {
        super.initViews();
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

        if (tag == TAG_GET_FRIENDS_LIST || tag == TAG_GET_FANS_LIST) {  //我的关注

            Friend friend = new Gson().fromJson(response.getJsonStr(), Friend.class);

            if (friend == null || friend.getList() == null || friend.getList().size() == 0) {
                if (friends != null) {
                    friends.clear();
                }
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
            }
            friends = friend.getList();
            setListData();

        } else if (AttentionActivity.TAG_ADD_FRIEND.equals(tag) || TAG_DEL_FRIEND.equals(tag)) {

            if (response.getCode() == 0) {

                ToastUtil.show(R.string.success);

                initData();
            } else {
                if(response == null || TextUtils.isEmpty(response.getMsg())){
                    return;
                }
                ToastUtil.show(response.getMsg());
            }

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

            getFansList();
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {

            getFriendsList();
        }

    }


    private void getFriendsList() {

        Api.getFriendsList(TAG_GET_FRIENDS_LIST, this, this);

    }

    private void getFansList() {
        Api.getFansList(TAG_GET_FANS_LIST, UserService.get().getUid() + "", start, limit, this, this);
    }

    private void addFriend(String friendId) {

        Api.addFriends(TAG_ADD_FRIEND, friendId, this, this);
    }

    private void delFriend(String friendId) {
        Api.delFriends(TAG_DEL_FRIEND, friendId, this, this);
    }


    private void setListData() {
        adapter = new QuickAdapter<Friend.list>(AttentionActivity.this, R.layout.item_list_attention, friends) {
            @Override
            protected void convert(final BaseAdapterHelper helper, Friend.list item) {

                helper.setText(R.id.tv_name, item.getRolename());

                helper.setImageResource(R.id.im_head, R.drawable.image1);

                ImageView headImageView = helper.getView(R.id.im_head);

                Picasso.with(context).load(Constants.HEAD_IMG_BASE+item.getPortrait()).placeholder(R.drawable.img_avatar_default)
                        .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(headImageView);

                final int position = helper.getPosition();

                if (type == MeFragment.AttentionType.ATTENTION.FANS) {

                    helper.setText(R.id.bt_attention, "关注");
                } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {

                    helper.setText(R.id.bt_attention, "取消");
                }

                helper.setOnClickListener(R.id.bt_attention, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position >= friends.size()) {
                            return;
                        }

                        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
                            String uid = friends.get(position).getUid() + "";
                            addFriend(uid);
                        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
                            String uid = friends.get(position).getFriend() + "";
                            delFriend(uid);
                        }
                    }
                });
            }
        };

        mListView.setAdapter(adapter);


    }
}
