package com.himeetu.ui.register;

import android.graphics.Color;
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
import com.himeetu.network.dic.Argument;
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
    private Country selectCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_choose);
        setStatusBarColor(R.color.black);
        setupToolbar(false, R.string.your_country);
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

                selectCountry = (Country)parent.getAdapter().getItem(position);
                adapter.setSelected(selectCountry);

                nextButton.setEnabled(true);
            }
        });

        SourceDateList = new ArrayList<>();
        SourceDateList.add(new Country("1", "Albania", "阿尔巴尼亚"));
        SourceDateList.add(new Country("2", "Algeria", "阿尔及利亚"));
        SourceDateList.add(new Country("3", "Afghanistan", "阿富汗"));
        SourceDateList.add(new Country("4", "Argentina", "阿根廷"));
        SourceDateList.add(new Country("5", "Aruba", "阿鲁巴"));
        SourceDateList.add(new Country("6", "Azerbaijan", "阿塞拜疆"));
        SourceDateList.add(new Country("7", "Andorra", "安道尔"));
        SourceDateList.add(new Country("8", "Angola", "安哥拉"));
        SourceDateList.add(new Country("9", "Anguilla", "安圭拉"));
        SourceDateList.add(new Country("10", "Antigua and Barbuda", "安提瓜和巴布达"));

        SourceDateList.add(new Country("11", "Austria", "奥地利"));
        SourceDateList.add(new Country("12", "Australia", "澳大利亚"));
        SourceDateList.add(new Country("13", "Barbados", "巴巴多斯"));
        SourceDateList.add(new Country("14", "Bahamas", "巴哈马"));
        SourceDateList.add(new Country("15", "Brazil", "巴西"));
        SourceDateList.add(new Country("16", "Belarus", "白俄罗斯"));
        SourceDateList.add(new Country("17", "Bermuda", "百慕大"));
        SourceDateList.add(new Country("18", "Bulgaria", "保加利亚"));
        SourceDateList.add(new Country("19", "Benin", "贝宁"));
        SourceDateList.add(new Country("20", "Belgium", "比利时"));

        SourceDateList.add(new Country("21", "Bolivia", "玻利维亚"));
        SourceDateList.add(new Country("22", "Bosnia and Herzegovina", "波斯尼亚和黑塞哥维那"));
        SourceDateList.add(new Country("23", "Belize", "伯利兹"));
        SourceDateList.add(new Country("24", "Bhutan", "不丹"));
        SourceDateList.add(new Country("25", "Burkina Faso", "布基纳法索"));
        SourceDateList.add(new Country("26", "Burundi", "布隆迪"));
        SourceDateList.add(new Country("27", "Brunei Darussalam", "文莱"));
        SourceDateList.add(new Country("28", "Cape Verde", "佛得角"));
        SourceDateList.add(new Country("29", "Congo", "刚果"));
        SourceDateList.add(new Country("30", "Colombia", "哥伦比亚"));

        SourceDateList.add(new Country("31", "Costa Rica", "哥斯达黎加"));
        SourceDateList.add(new Country("32", "Cuba", "古巴"));
        SourceDateList.add(new Country("33", "Canada", "加拿大"));
        SourceDateList.add(new Country("34", "Cambodia", "柬埔寨"));
        SourceDateList.add(new Country("35", "Czech Repoublic", "捷克"));
        SourceDateList.add(new Country("36", "Cameroon", "喀麦隆"));
        SourceDateList.add(new Country("37", "Cayman Islands", "开曼群岛"));
        SourceDateList.add(new Country("38", "Comoros", "科摩罗"));
        SourceDateList.add(new Country("39", "Cote d'Ivoire", "科特迪瓦"));
        SourceDateList.add(new Country("40", "Croatia", "克罗地亚"));

        SourceDateList.add(new Country("41", "Cook Islands", "库克群岛"));
        SourceDateList.add(new Country("42", "Cyprus", "塞浦路斯"));
        SourceDateList.add(new Country("43", "Chad", "乍得"));
        SourceDateList.add(new Country("44", "Chile", "智利"));
        SourceDateList.add(new Country("45", "Central Africa", "中非"));
        SourceDateList.add(new Country("46", "China", "中国"));
        SourceDateList.add(new Country("47", "Denmark", "丹麦"));
        SourceDateList.add(new Country("48", "Dominican Republic", "多米尼加共和国"));
        SourceDateList.add(new Country("49", "Dominica", "多米尼克"));
        SourceDateList.add(new Country("50", "Djibouti", "吉布提"));

        SourceDateList.add(new Country("51", "Egyp", "埃及"));
        SourceDateList.add(new Country("52", "Ethiopia", "埃塞俄比亚"));
        SourceDateList.add(new Country("53", "Estonia", "爱沙尼亚"));
        SourceDateList.add(new Country("54", "Equatorial Guinea", "赤道几内亚"));
        SourceDateList.add(new Country("55", "East Timor", "东帝汶"));
        SourceDateList.add(new Country("56", "Ecuador", "厄瓜多尔"));
        SourceDateList.add(new Country("57", "Eritrea", "厄立特里亚"));
        SourceDateList.add(new Country("58", "France", "法国"));
        SourceDateList.add(new Country("59", "Faroe Islands", "法罗群岛"));
        SourceDateList.add(new Country("60", "French Polynesia", "法属波利尼西亚"));

        SourceDateList.add(new Country("61", "Fiji", "斐济"));
        SourceDateList.add(new Country("62", "Finland", "芬兰"));
        SourceDateList.add(new Country("63", "Gambia", "冈比亚"));
        SourceDateList.add(new Country("64", "Grenada", "格林纳达"));
        SourceDateList.add(new Country("65", "Greenland", "格陵兰"));
        SourceDateList.add(new Country("66", "Georgia", "格鲁吉亚"));
        SourceDateList.add(new Country("67", "Guadeloupe", "瓜德罗普"));
        SourceDateList.add(new Country("68", "Guam", "关岛"));
        SourceDateList.add(new Country("69", "Guyana", "圭亚那"));
        SourceDateList.add(new Country("70", "Guinea", "几内亚"));

        SourceDateList.add(new Country("71", "Gabon", "加蓬"));
        SourceDateList.add(new Country("72", "Guatemala", "危地马拉"));
        SourceDateList.add(new Country("73", "Greece", "希腊"));
        SourceDateList.add(new Country("74", "Haiti", "海地"));
        SourceDateList.add(new Country("75", "Honduras", "洪都拉斯"));
        SourceDateList.add(new Country("76", "Hong Kong", "中国 香港"));
        SourceDateList.add(new Country("77", "Hungary", "匈牙利"));
        SourceDateList.add(new Country("78", "Ireland", "爱尔兰"));
        SourceDateList.add(new Country("79", "Iceland", "冰岛"));
        SourceDateList.add(new Country("80", "Iraq", "伊拉克"));

        SourceDateList.add(new Country("81", "Iran", "伊朗"));
        SourceDateList.add(new Country("82", "Israel", "以色列"));
        SourceDateList.add(new Country("83", "Italy", "意大利"));
        SourceDateList.add(new Country("84", "India", "印度"));
        SourceDateList.add(new Country("85", "Indonesia", "印度尼西亚"));
        SourceDateList.add(new Country("86", "Japan", "日本"));
        SourceDateList.add(new Country("87", "Jamaica", "牙买加"));
        SourceDateList.add(new Country("88", "Jordan", "约旦"));
        SourceDateList.add(new Country("89", "Korea,Democratic People's", "朝鲜"));
        SourceDateList.add(new Country("90", "Kazakhstan", "哈萨克斯坦"));

        SourceDateList.add(new Country("91", "Korea", "韩国"));
        SourceDateList.add(new Country("92", "Kiribati", "基里巴斯"));
        SourceDateList.add(new Country("93", "Kyrgyzstan", "吉尔吉斯斯坦"));
        SourceDateList.add(new Country("94", "Kuwait", "科威特"));
        SourceDateList.add(new Country("95", "Kenya", "肯尼亚"));
        SourceDateList.add(new Country("96", "Latvia", "拉脱维亚"));
        SourceDateList.add(new Country("97", "Lao", "老挝"));
        SourceDateList.add(new Country("98", "Lebanon", "黎巴嫩"));
        SourceDateList.add(new Country("99", "Liberia", "利比里亚"));
        SourceDateList.add(new Country("100", "Libya", "利比亚"));

        SourceDateList.add(new Country("101", "Lithuania", "立陶宛"));
        SourceDateList.add(new Country("102", "Liechtenstein", "列支敦士登"));
        SourceDateList.add(new Country("103", "Luxembourg", "卢森堡"));
        SourceDateList.add(new Country("104", "Macau", "澳门"));
        SourceDateList.add(new Country("105", "Malta", "马耳他"));
        SourceDateList.add(new Country("106", "Maldives", "马尔代夫"));
        SourceDateList.add(new Country("107", "Malaysia", "马来西亚"));
        SourceDateList.add(new Country("108", "Mali", "马里"));
        SourceDateList.add(new Country("109", "Martinique", "马提尼克"));
        SourceDateList.add(new Country("110", "Mauritius", "毛里求斯"));

        SourceDateList.add(new Country("111", "Mauritania", "毛里塔尼亚"));
        SourceDateList.add(new Country("112", "Mongolia", "蒙古"));
        SourceDateList.add(new Country("113", "Montserrat", "蒙特塞拉特"));
        SourceDateList.add(new Country("114", "Micronesia", "密克罗尼西亚"));
        SourceDateList.add(new Country("115", "Myanmar", "缅甸"));
        SourceDateList.add(new Country("116", "Moldova", "摩尔多瓦"));
        SourceDateList.add(new Country("117", "Morocco", "摩洛哥"));
        SourceDateList.add(new Country("118", "Monaco", "摩纳哥"));
        SourceDateList.add(new Country("119", "Mexico", "墨西哥"));
        SourceDateList.add(new Country("120", "Netherlands", "荷兰"));

        SourceDateList.add(new Country("121", "Netherlands Antilles", "荷属安的列斯"));
        SourceDateList.add(new Country("122", "Nauru", "瑙鲁"));
        SourceDateList.add(new Country("123", "Nepal", "尼泊尔"));
        SourceDateList.add(new Country("124", "Nicaragua", "尼加拉瓜"));
        SourceDateList.add(new Country("125", "Niger", "尼日尔"));
        SourceDateList.add(new Country("126", "Niue", "纽埃"));
        SourceDateList.add(new Country("127", "Norway", "挪威"));
        SourceDateList.add(new Country("128", "New Zealand", "新西兰"));
        SourceDateList.add(new Country("129", "Oman", "阿曼"));
        SourceDateList.add(new Country("130", "Papua New Guinea", "巴布亚新几内亚"));

        SourceDateList.add(new Country("131", "Pakistan", "巴基斯坦"));
        SourceDateList.add(new Country("132", "Paraguay", "巴拉圭"));
        SourceDateList.add(new Country("133", "Palestine", "巴勒斯坦"));
        SourceDateList.add(new Country("134", "Panama", "巴拿马"));
        SourceDateList.add(new Country("135", "Palau", "帕劳"));
        SourceDateList.add(new Country("136", "Puerto Rico", "波多黎各"));
        SourceDateList.add(new Country("137", "Poland", "波兰"));
        SourceDateList.add(new Country("138", "Philippines", "菲律宾"));
        SourceDateList.add(new Country("139", "Peru", "秘鲁"));
        SourceDateList.add(new Country("140", "Pitcairn Islands Group", "皮特凯恩群岛"));

        SourceDateList.add(new Country("141", "Portugal", "葡萄牙"));
        SourceDateList.add(new Country("142", "Qatar", "卡塔尔"));
        SourceDateList.add(new Country("143", "Russia", "俄罗斯"));
        SourceDateList.add(new Country("144", "Rwanda", "卢旺达"));
        SourceDateList.add(new Country("145", "Romania", "罗马尼亚"));
        SourceDateList.add(new Country("146", "South Africa", "南非"));
        SourceDateList.add(new Country("147", "Sweden", "瑞典"));
        SourceDateList.add(new Country("148", "Switzerland", "瑞士"));
        SourceDateList.add(new Country("149", "Sierra leone", "塞拉利昂"));
        SourceDateList.add(new Country("150", "Senegal", "塞内加尔"));

        SourceDateList.add(new Country("151", "Seychells", "塞舌尔"));
        SourceDateList.add(new Country("152", "Saint lucia", "圣卢西亚"));
        SourceDateList.add(new Country("153", "San Marion", "圣马力诺"));
        SourceDateList.add(new Country("154", "Sri Lanka", "斯里兰卡"));
        SourceDateList.add(new Country("155", "Slovakia", "斯洛伐克"));
        SourceDateList.add(new Country("156", "Slovenia", "斯洛文尼亚"));
        SourceDateList.add(new Country("157", "Sudan", "苏丹"));
        SourceDateList.add(new Country("158", "Suriname", "苏里南"));
        SourceDateList.add(new Country("159", "Somalia", "索马里"));
        SourceDateList.add(new Country("160", "Solomon Islands", "所罗门群岛"));

        SourceDateList.add(new Country("161", "Spain ", "西班牙"));
        SourceDateList.add(new Country("162", "Singapore", "新加坡"));
        SourceDateList.add(new Country("163", "Syria", "叙利亚"));
        SourceDateList.add(new Country("164", "Togo", "多哥"));
        SourceDateList.add(new Country("165", "Thailand", "泰国"));
        SourceDateList.add(new Country("166", "Tanzania", "坦桑尼亚"));
        SourceDateList.add(new Country("167", "Tunisia", "突尼斯"));
        SourceDateList.add(new Country("168", "Tuvalu", "图瓦卢"));
        SourceDateList.add(new Country("169", "Turkey", "土耳其"));
        SourceDateList.add(new Country("170", "Turkmenistan", "土库曼斯坦"));

        SourceDateList.add(new Country("171", "Tokelau", "托克劳"));
        SourceDateList.add(new Country("172", "Western Samoa", "西萨摩亚"));
        SourceDateList.add(new Country("173", "Vatican", "梵蒂冈"));
        SourceDateList.add(new Country("174", "Vanuatu", "瓦努阿图"));
        SourceDateList.add(new Country("175", "Venezuela", "委内瑞拉"));
        SourceDateList.add(new Country("176", "Viet Nam", "越南"));
        SourceDateList.add(new Country("177", "United Arab Emirates", "阿联酋"));
        SourceDateList.add(new Country("178", "United States", "美国"));
        SourceDateList.add(new Country("179", "Uganda", "乌干达"));
        SourceDateList.add(new Country("180", "Ukraine", "乌克兰"));

        SourceDateList.add(new Country("181", "Uruguay", "乌拉圭"));
        SourceDateList.add(new Country("182", "Uzbekistan", "乌兹别克斯坦"));
        SourceDateList.add(new Country("183", "United Kingdom", "英国"));
        SourceDateList.add(new Country("184", "Yemen", "也门"));
        SourceDateList.add(new Country("185", "Zimbabwe", "津巴布韦"));
        SourceDateList.add(new Country("186", "Zambia", "赞比亚"));
        SourceDateList.add(new Country("187", "Germany", "德国"));


//        SourceDateList.add(new Country("25", "Bahrain", "巴林"));
//        SourceDateList.add(new Country("108", "Madagascar", "马达加斯加"));
//        SourceDateList.add(new Country("120", "Bangladesh", "孟加拉国"));
//        SourceDateList.add(new Country("153", "Tajikistan", "塔吉克斯坦"));
//        SourceDateList.add(new Country("177", "Armenia", "亚美尼亚"));


        Collections.sort(SourceDateList, new CountryComparator());
        adapter = new SortAdapter(this, SourceDateList);


        sortListView.setAdapter(adapter);
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        nextButton.setOnClickListener(this);

        findViewById(R.id.layout_has_account).setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                goNext();
                break;
            case R.id.layout_has_account:
                toLoginPage();
                break;
        }
    }

    private void goNext(){
        if(selectCountry == null){
            return;
        }

        String  countryCode = selectCountry.getId();
        String username = getIntent().getStringExtra(Argument.USERNAME);
        String password = getIntent().getStringExtra(Argument.PASSWORD);
        NavHelper.toIdentityPage(this, username, password, countryCode);
    }

    private void toLoginPage(){
        NavHelper.toLoginPage(this);
        finish();
    }
}
