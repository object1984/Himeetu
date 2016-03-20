package com.himeetu.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.app.Api;
import com.himeetu.model.GsonResult;
import com.himeetu.model.Message;
import com.himeetu.model.Talk;
import com.himeetu.network.dic.Argument;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.JsonUtil;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 16/3/20.
 */
public class TalkListActivity extends BaseVolleyActivity implements AdapterView.OnItemClickListener {
    public static final String TAG_GET_TALKS = "TAG_GET_TALKS";
    private int start = 0;
    private int limit = 20;

    private ListView talkListView;
    private QuickAdapter<Talk> talkQuickAdapter;
    private List<Talk> talkList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_talk_list);
        setupToolbar(true, R.string.talk_list);
        setStatusBarColor(R.color.black);

        init();

        getTalks();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        talkListView = (ListView)findViewById(R.id.swipe_target);

        talkQuickAdapter = new QuickAdapter<Talk>(this, R.layout.item_choose_talk) {
            @Override
            protected void convert(BaseAdapterHelper helper, Talk item) {
                helper.setText(R.id.text_talk_name, String.format("#%s", item.getName()));
            }


        };

        talkListView.setAdapter(talkQuickAdapter);

        talkListView.setOnItemClickListener(this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        if(TAG_GET_TALKS.equals(tag)){
            talkQuickAdapter.clear();

            JSONObject jsonObject = JsonUtil.getJSONObject(response.getJsonStr());

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
            Gson gson = gsonBuilder.create();

            Type listType = new TypeToken<List<Talk>>() {}.getType();
            List<Talk> messages  = gson.fromJson(JsonUtil.getJSONArray(jsonObject, "list").toString(), listType);

            if(messages != null && messages.size() != 0){
                talkQuickAdapter.addAll(messages);
            }

        }
    }

    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }

    private void getTalks(){
        Api.getTalks(TAG_GET_TALKS, start, limit, this, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Talk talk = (Talk) parent.getAdapter().getItem(position);

        if(talk != null){
            Intent resultIntent = new Intent();
            resultIntent.putExtra(Argument.TALK, talk);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
