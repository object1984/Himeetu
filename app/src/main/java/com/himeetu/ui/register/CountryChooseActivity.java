package com.himeetu.ui.register;

import android.os.Bundle;
import android.test.ServiceTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.himeetu.R;
import com.himeetu.adapter.SortAdapter;
import com.himeetu.app.NavHelper;
import com.himeetu.model.Country;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.CountryComparator;
import com.himeetu.view.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by object1984 on 15/12/2.
 */
public class CountryChooseActivity extends BaseActivity implements View.OnClickListener {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog;
    private SortAdapter adapter;
    private List<Country> SourceDateList;

    private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_country_choose);

        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        nextButton = (Button)findViewById(R.id.btn_next);
        sideBar = (SideBar) findViewById(R.id.sidrbar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });

        sortListView = (ListView) findViewById(R.id.list_country);

        View footerView = LayoutInflater.from(this).inflate(R.layout.item_list_footer_country, sortListView, false);
        sortListView.addFooterView(footerView);

        sortListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplication(), ((Country)adapter.getItem(position)).getEnName(), Toast.LENGTH_SHORT).show();

                Country country = (Country)parent.getAdapter().getItem(position);
                adapter.setSelected(country);

                nextButton.setEnabled(true);
            }
        });

        SourceDateList = new ArrayList<>();
        SourceDateList.add(new Country("China", "中国"));
        SourceDateList.add(new Country("Albania", "阿尔巴尼亚"));
        SourceDateList.add(new Country("Benin", "贝宁"));
        SourceDateList.add(new Country("Germany", "德国"));
        SourceDateList.add(new Country("Korea", "韩国"));
        SourceDateList.add(new Country("Japan", "日本"));
        SourceDateList.add(new Country("Canada", "加拿大"));
        SourceDateList.add(new Country("Qatar", "卡塔尔"));
        SourceDateList.add(new Country("Guinea", "几内亚"));
        SourceDateList.add(new Country("Ireland", "爱尔兰"));
        SourceDateList.add(new Country("Estonia", "爱沙尼亚"));
        SourceDateList.add(new Country("Andorra", "安道尔"));
        SourceDateList.add(new Country("Australia", "澳大利亚"));
        SourceDateList.add(new Country("Lao", "老挝"));
        Collections.sort(SourceDateList, new CountryComparator());
        adapter = new SortAdapter(this, SourceDateList);


        sortListView.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        nextButton.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
        setTitleText(R.string.your_country);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                goNext();
                break;
        }
    }

    private void goNext(){
        NavHelper.toIdentityPage(this);
    }
}
