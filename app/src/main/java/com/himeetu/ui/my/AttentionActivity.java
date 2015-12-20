package com.himeetu.ui.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.himeetu.R;
import com.himeetu.adapter.BaseAdapterHelper;
import com.himeetu.adapter.QuickAdapter;
import com.himeetu.model.GsonResult;
import com.himeetu.model.PersonState;
import com.himeetu.model.UserImg;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.ui.main.MeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关注/粉丝 页面
 */
public class AttentionActivity extends BaseVolleyActivity {
    private MeFragment.AttentionType type;
    private ListView mListView;
    private List<PersonState> lists;
    private Map<Integer,Boolean> flagMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        type = (MeFragment.AttentionType) getIntent().getSerializableExtra(MeFragment.TYPE);

        initToolBar();

        super.init();

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

        for(int i = 0; i < 20;i++){
            lists.add(new PersonState());
        }

        mListView.setAdapter(new QuickAdapter<PersonState>(AttentionActivity.this,R.layout.item_list_attention,lists) {
            @Override
            protected void convert(final BaseAdapterHelper helper, PersonState item) {

                helper.setText(R.id.tv_name, "test");

                helper.setImageResource(R.id.im_head,R.drawable.image1);

                int position = helper.getPosition();

                boolean isChecked = false;

                if(flagMap.containsKey(position)){

                    isChecked = flagMap.get(position);

                }

                helper.setChecked(R.id.bt_attention,isChecked);

                helper.setOnCheckedListener(R.id.bt_attention, new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        flagMap.put(helper.getPosition(),isChecked);
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
    }

    private void initToolBar() {
        int title = 0;
        if (type == MeFragment.AttentionType.ATTENTION.FANS) {
            title = R.string.consider_me;
        } else if (type == MeFragment.AttentionType.ATTENTION.ATTENTION) {
            title = R.string.me_consider;
        }

        setupToolbar(true,title);
        setToolBarColor(getResources().getColor(R.color.white));
//        setRightTextAndVisible(0, View.INVISIBLE);
    }

}
