package com.himeetu.ui.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.NavHelper;
import com.himeetu.model.GsonResult;
import com.himeetu.model.HiActivity;
import com.himeetu.model.Recommend;
import com.himeetu.model.Talk;
import com.himeetu.model.User;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.JsonUtil;
import com.himeetu.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by object1984 on 15/12/23.
 */
public class SearchActivity extends BaseVolleyActivity implements View.OnClickListener, TextView.OnEditorActionListener {
    private static final String TAG = SearchActivity.class.getCanonicalName();
    private static final String TAG_API_SEARCH_TALK = "TAG_API_SEARCH_TALK";
    private static final String TAG_API_SEARCH_USER = "TAG_API_SEARCH_USER";
    private static final String TAG_API_SEARCH_ACTIVITY = "TAG_API_SEARCH_ACTIVITY";

    private ViewGroup resultView;
    private View normalView;
    private String param = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setStatusBarColor(R.color.app_default);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();

        resultView = (ViewGroup)findViewById(R.id.layout_result);
        normalView = findViewById(R.id.layout_normal);

    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        findViewById(R.id.text_cancel).setOnClickListener(this);

        ((EditText)findViewById(R.id.text_search)).setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_cancel:
                cancel();
                break;
        }
    }

    private void cancel(){
        NavHelper.finish(this);
    }

    private void searchUser(String user){
        Api.searchUser(TAG_API_SEARCH_USER, user, 0, 3, this, this);
    }

    private void searchActivity(String activity){
        Api.searchActivity(TAG_API_SEARCH_ACTIVITY, activity, 0, 3, this, this);
    }

    private void searchTalk(String talk){
        Api.searchTalk(TAG_API_SEARCH_TALK, talk, 0, 3, this, this);
    }

    private void search(String param){
        normalView.setVisibility(View.GONE);
        resultView.removeAllViews();
        searchUser(param);
        searchActivity(param);
        searchTalk(param);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String param = v.getText().toString();
            if(TextUtils.isEmpty(param)){
                return true;
            }
            this.param = param;
            search(param);
        }

        return false;
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);

        if(TAG_API_SEARCH_USER.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            if(jsonObject == null){
                return;
            }

            Type listType = new TypeToken<List<User>>() {
            }.getType();

            JSONArray listJsonAry = JsonUtil.getJSONArray(jsonObject, "list");

            if(listJsonAry == null){
                return;
            }
            List<User> users  = new Gson().fromJson(listJsonAry.toString(), listType);

            if(users != null){
                View userTitleView = View.inflate(this, R.layout.item_search_title, null);
                TextView userTextView = (TextView) userTitleView.findViewById(R.id.text_title);
                userTextView.setText("用户");
                resultView.addView(userTitleView);

                for(int i=0; i<users.size(); i++){
                    final User user = users.get(i);
                    View userView= View.inflate(this, R.layout.item_search_user, null);
                    ImageView userHeadImageView = (ImageView) userView.findViewById(R.id.img_user);
                    TextView  userNicknameTextView = (TextView) userView.findViewById(R.id.text_nickname);
                    TextView  userLikeTextView = (TextView) userView.findViewById(R.id.text_like);

                    userNicknameTextView.setText(user.getNickname());
//                    userNicknameTextView.setText(user.getNickname());
                    Picasso.with(this).load(user.getPortrait()).placeholder(R.drawable.img_avatar_default)
                            .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(userHeadImageView);
                    resultView.addView(userView);


                    userView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavHelper.toUserActivity(SearchActivity.this, user.getUid() + "");
                        }
                    });
                }

                if (users.size() == 3){
                    View userMoreView = View.inflate(this, R.layout.item_search_more, null);

                    userMoreView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavHelper.toSearchMoreUser(SearchActivity.this, param );
                        }
                    });
                    resultView.addView(userMoreView);
                }

            }
        }

        if(TAG_API_SEARCH_ACTIVITY.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            if(jsonObject == null){
                return;
            }

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = gsonBuilder.create();
            Type listType = new TypeToken<List<HiActivity>>() {
            }.getType();

            JSONArray listJsonAry = JsonUtil.getJSONArray(jsonObject, "list");

            if(listJsonAry == null){
                return;
            }
            List<HiActivity> activities  = gson.fromJson(listJsonAry.toString(), listType);

            if(activities != null){
                View activityTitleView = View.inflate(this, R.layout.item_search_title, null);
                TextView activityTextView = (TextView) activityTitleView.findViewById(R.id.text_title);
                activityTextView.setText("活动");
                resultView.addView(activityTitleView);

                for(int i=0; i<activities.size(); i++){
                    HiActivity hiActivity = activities.get(i);
                    View userView= View.inflate(this, R.layout.item_search_activity, null);
                    ImageView activityImageView = (ImageView) userView.findViewById(R.id.img_activity);
                    TextView  activityNameTextView = (TextView) userView.findViewById(R.id.text_activity);
                    TextView  userLikeTextView = (TextView) userView.findViewById(R.id.text_like);

                    activityNameTextView.setText(hiActivity.getName());
//                    userNicknameTextView.setText(user.getNickname());
                    Picasso.with(this).load(hiActivity.getImgPath()).placeholder(R.drawable.img_avatar_default)
                            .error(R.drawable.img_avatar_default).fit().into(activityImageView);
                    resultView.addView(userView);
                }

                if (activities.size() == 3){
                    resultView.addView(View.inflate(this, R.layout.item_search_more, null));
                }
            }
        }

        if(TAG_API_SEARCH_TALK.equals(tag)){
            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            if(jsonObject == null){
                return;
            }

            Type listType = new TypeToken<List<Talk>>() {
            }.getType();

            JSONArray listJsonAry = JsonUtil.getJSONArray(jsonObject, "list");

            if(listJsonAry == null){
                return;
            }
            List<Talk> talks  = new Gson().fromJson(listJsonAry.toString(), listType);

            if(talks != null){
                View userTitleView = View.inflate(this, R.layout.item_search_title, null);
                TextView userTitleTextView = (TextView) userTitleView.findViewById(R.id.text_title);
                userTitleTextView.setText("动态");
                resultView.addView(userTitleView);

                for(int i=0; i<talks.size(); i++){
                    Talk talk = talks.get(i);
                    View talkView= View.inflate(this, R.layout.item_search_talk, null);
                    ImageView talkImageView = (ImageView) talkView.findViewById(R.id.img_talk);
                    TextView  talkContentTextView = (TextView) talkView.findViewById(R.id.text_content);
                    TextView  userLikeTextView = (TextView) talkView.findViewById(R.id.text_like);

                    talkContentTextView.setText(URLDecoder.decode(talk.getDes()));
//                    userNicknameTextView.setText(user.getNickname());
                    Picasso.with(this).load(talk.getImgPath()).placeholder(R.drawable.img_default)
                            .error(R.drawable.img_default).fit().into(talkImageView);
                    resultView.addView(talkView);

                }

                if (talks.size() == 3){
                    resultView.addView(View.inflate(this, R.layout.item_search_more, null));
                }
            }
        }
    }
}
