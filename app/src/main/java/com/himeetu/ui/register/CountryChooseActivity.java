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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_country_choose);
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

                Country country = (Country)parent.getAdapter().getItem(position);
                adapter.setSelected(country);

                nextButton.setEnabled(true);
            }
        });

        SourceDateList = new ArrayList<>();
        SourceDateList.add(new Country("0", "China", "中国"));
        SourceDateList.add(new Country("1", "Albania", "阿尔巴尼亚"));
        SourceDateList.add(new Country("2", "Algeria", "阿尔及利亚"));
        SourceDateList.add(new Country("3", "Afghanistan", "阿富汗"));
        SourceDateList.add(new Country("4", "Argentina", "阿根廷"));
        SourceDateList.add(new Country("5", "United Arab Emirates", "阿联酋"));
        SourceDateList.add(new Country("6", "Aruba", "阿鲁巴"));
        SourceDateList.add(new Country("7", "Oman", "阿曼"));
        SourceDateList.add(new Country("8", "Azerbaijan", "阿塞拜疆"));
        SourceDateList.add(new Country("9", "Egyp", "埃及"));
        SourceDateList.add(new Country("10", "Ethiopia", "埃塞俄比亚"));
        SourceDateList.add(new Country("11", "Ireland", "爱尔兰"));
        SourceDateList.add(new Country("12", "Estonia", "爱沙尼亚"));
        SourceDateList.add(new Country("13", "Andorra", "安道尔"));
        SourceDateList.add(new Country("14", "Angola", "安哥拉"));
        SourceDateList.add(new Country("15", "Antigua and Barbuda", "安提瓜和巴布达"));
        SourceDateList.add(new Country("16", "Austria", "奥地利"));
        SourceDateList.add(new Country("17", "Australia", "澳大利亚"));
        SourceDateList.add(new Country("18", "Macau", "澳门"));
        SourceDateList.add(new Country("19", "Barbados", "巴巴多斯"));
        SourceDateList.add(new Country("20", "Papua New Guinea", "巴布亚新几内亚"));
        SourceDateList.add(new Country("21", "Bahamas", "巴哈马"));
        SourceDateList.add(new Country("22", "Pakistan", "巴基斯坦"));
        SourceDateList.add(new Country("23", "Paraguay", "巴拉圭"));
        SourceDateList.add(new Country("24", "Palestine", "巴勒斯坦"));
        SourceDateList.add(new Country("25", "Bahrain", "巴林"));
        SourceDateList.add(new Country("26", "Panama", "巴拿马"));
        SourceDateList.add(new Country("27", "Brazil", "巴西"));
        SourceDateList.add(new Country("28", "Belarus", "白俄罗斯"));
        SourceDateList.add(new Country("29", "Bermuda", "百慕大"));
        SourceDateList.add(new Country("30", "Bulgaria", "保加利亚"));
        SourceDateList.add(new Country("31", "Palau", "帕劳"));
        SourceDateList.add(new Country("32", "Benin", "贝宁"));
        SourceDateList.add(new Country("33", "Belgium", "比利时"));
        SourceDateList.add(new Country("34", "Iceland", "冰岛"));
        SourceDateList.add(new Country("35", "Puerto Rico", "波多黎各"));
        SourceDateList.add(new Country("36", "Poland", "波兰"));
        SourceDateList.add(new Country("37", "Bolivia", "玻利维亚"));
        SourceDateList.add(new Country("38", "Bosnia and Herzegovina", "波斯尼亚和黑塞哥维那"));
        SourceDateList.add(new Country("39", "Belize", "伯利兹"));
        SourceDateList.add(new Country("40", "Bhutan", "不丹"));
        SourceDateList.add(new Country("41", "Burkina Faso", "布基纳法索"));
        SourceDateList.add(new Country("42", "Burundi", "布隆迪"));
        SourceDateList.add(new Country("43", "Korea,Democratic People's", "朝鲜"));
        SourceDateList.add(new Country("44", "Equatorial Guinea", "赤道几内亚"));
        SourceDateList.add(new Country("45", "Denmark", "丹麦"));
        SourceDateList.add(new Country("46", "Germany", "德国"));
        SourceDateList.add(new Country("47", "East Timor", "东帝汶"));
        SourceDateList.add(new Country("48", "Togo", "多哥"));
        SourceDateList.add(new Country("49", "Dominican Republic", "多米尼加共和国"));
        SourceDateList.add(new Country("50", "Dominica", "多米尼克"));
        SourceDateList.add(new Country("51", "Russia", "俄罗斯"));
        SourceDateList.add(new Country("52", "Ecuador", "厄瓜多尔"));
        SourceDateList.add(new Country("53", "Eritrea", "厄立特里亚"));
        SourceDateList.add(new Country("54", "France", "法国"));
        SourceDateList.add(new Country("55", "Faroe Islands", "法罗群岛"));
        SourceDateList.add(new Country("56", "French Polynesia", "法属波利尼西亚"));
        SourceDateList.add(new Country("57", "Vatican", "梵蒂冈"));
        SourceDateList.add(new Country("58", "Philippines", "法属波利尼西亚"));
        SourceDateList.add(new Country("59", "French Polynesia", "菲律宾"));
        SourceDateList.add(new Country("60", "Fiji", "斐济"));
        SourceDateList.add(new Country("61", "Finland", "芬兰"));
        SourceDateList.add(new Country("62", "Cape Verde", "佛得角"));
        SourceDateList.add(new Country("63", "Gambia", "冈比亚"));
        SourceDateList.add(new Country("64", "Congo", "刚果"));
        SourceDateList.add(new Country("65", "Colombia", "哥伦比亚"));
        SourceDateList.add(new Country("66", "Costa Rica", "哥斯达黎加"));
        SourceDateList.add(new Country("67", "Grenada", "格林纳达"));
        SourceDateList.add(new Country("68", "Greenland", "格陵兰"));
        SourceDateList.add(new Country("69", "Georgia", "格鲁吉亚"));
        SourceDateList.add(new Country("70", "Cuba", "古巴"));
        SourceDateList.add(new Country("71", "Guadeloupe", "瓜德罗普"));
        SourceDateList.add(new Country("72", "Guam", "关岛"));
        SourceDateList.add(new Country("73", "Guyana", "圭亚那"));
        SourceDateList.add(new Country("74", "Kazakhstan", "哈萨克斯坦"));
        SourceDateList.add(new Country("75", "Haiti", "海地"));
        SourceDateList.add(new Country("76", "Korea", "韩国"));
        SourceDateList.add(new Country("77", "Netherlands", "荷兰"));
        SourceDateList.add(new Country("78", "Netherlands Antilles", "荷属安的列斯"));
        SourceDateList.add(new Country("79", "Honduras", "洪都拉斯"));
        SourceDateList.add(new Country("80", "Kiribati", "基里巴斯"));
        SourceDateList.add(new Country("81", "Djibouti", "吉布提"));
        SourceDateList.add(new Country("82", "Kyrgyzstan", "吉尔吉斯斯坦"));
        SourceDateList.add(new Country("83", "Guinea", "几内亚"));
        SourceDateList.add(new Country("84", "Canada", "加拿大"));
        SourceDateList.add(new Country("85", "Gabon", "加蓬"));
        SourceDateList.add(new Country("86", "Cambodia", "柬埔寨"));
        SourceDateList.add(new Country("87", "Czech Repoublic", "捷克"));
        SourceDateList.add(new Country("88", "Zimbabwe", "津巴布韦"));
        SourceDateList.add(new Country("89", "Cameroon", "喀麦隆"));
        SourceDateList.add(new Country("90", "Qatar", "卡塔尔"));
        SourceDateList.add(new Country("91", "Cayman Islands", "开曼群岛"));
        SourceDateList.add(new Country("92", "Comoros", "科摩罗"));
        SourceDateList.add(new Country("93", "Cote d'Ivoire", "科特迪瓦"));
        SourceDateList.add(new Country("94", "Kuwait", "科威特"));
        SourceDateList.add(new Country("95", "Croatia", "克罗地亚"));
        SourceDateList.add(new Country("96", "Kenya", "肯尼亚"));
        SourceDateList.add(new Country("97", "Cook Islands", "库克群岛"));
        SourceDateList.add(new Country("98", "Latvia", "拉脱维亚"));
        SourceDateList.add(new Country("99", "Lao", "老挝"));
        SourceDateList.add(new Country("100", "Lebanon", "黎巴嫩"));
        SourceDateList.add(new Country("101", "Liberia", "利比里亚"));
        SourceDateList.add(new Country("102", "Libya", "利比亚"));
        SourceDateList.add(new Country("103", "Lithuania", "立陶宛"));
        SourceDateList.add(new Country("104", "Liechtenstein", "列支敦士登"));
        SourceDateList.add(new Country("105", "Luxembourg", "卢森堡"));
        SourceDateList.add(new Country("106", "Rwanda", "卢旺达"));
        SourceDateList.add(new Country("107", "Romania", "罗马尼亚"));
        SourceDateList.add(new Country("108", "Madagascar", "马达加斯加"));
        SourceDateList.add(new Country("109", "Malta", "马耳他"));
        SourceDateList.add(new Country("111", "Maldives", "马尔代夫"));
        SourceDateList.add(new Country("112", "Malaysia", "马来西亚"));
        SourceDateList.add(new Country("113", "Mali", "马里"));
        SourceDateList.add(new Country("114", "Martinique", "马提尼克"));
        SourceDateList.add(new Country("115", "Mauritius", "毛里求斯"));
        SourceDateList.add(new Country("116", "Mauritania", "毛里塔尼亚"));
        SourceDateList.add(new Country("117", "United States", "美国"));
        SourceDateList.add(new Country("118", "Mongolia", "蒙古"));
        SourceDateList.add(new Country("119", "Montserrat", "蒙特塞拉特"));
        SourceDateList.add(new Country("120", "Bangladesh", "孟加拉国"));
        SourceDateList.add(new Country("121", "Peru", "秘鲁"));
        SourceDateList.add(new Country("122", "Micronesia", "密克罗尼西亚"));
        SourceDateList.add(new Country("123", "Myanmar", "缅甸"));
        SourceDateList.add(new Country("124", "Moldova", "摩尔多瓦"));
        SourceDateList.add(new Country("125", "Morocco", "摩洛哥"));
        SourceDateList.add(new Country("126", "Monaco", "摩纳哥"));
        SourceDateList.add(new Country("127", "Mexico", "墨西哥"));
        SourceDateList.add(new Country("128", "South Africa", "南非"));
        SourceDateList.add(new Country("129", "Nauru", "瑙鲁"));
        SourceDateList.add(new Country("130", "Nepal", "尼泊尔"));
        SourceDateList.add(new Country("131", "Nicaragua", "尼加拉瓜"));
        SourceDateList.add(new Country("132", "Niger", "尼日尔"));
        SourceDateList.add(new Country("133", "Niue", "纽埃"));
        SourceDateList.add(new Country("134", "Norway", "挪威"));
        SourceDateList.add(new Country("135", "Pitcairn Islands Group", "皮特凯恩群岛"));
        SourceDateList.add(new Country("136", "Portugal", "葡萄牙"));
        SourceDateList.add(new Country("137", "Japan", "日本"));
        SourceDateList.add(new Country("138", "Sweden", "瑞典"));
        SourceDateList.add(new Country("139", "Switzerland", "瑞士"));
        SourceDateList.add(new Country("140", "Sierra leone", "塞拉利昂"));
        SourceDateList.add(new Country("141", "Senegal", "塞内加尔"));
        SourceDateList.add(new Country("142", "Cyprus", "塞浦路斯"));
        SourceDateList.add(new Country("143", "Seychells", "塞舌尔"));
        SourceDateList.add(new Country("144", "Saint lucia", "圣卢西亚"));
        SourceDateList.add(new Country("145", "San Marion", "圣马力诺"));
        SourceDateList.add(new Country("146", "Sri Lanka", "斯里兰卡"));
        SourceDateList.add(new Country("147", "Slovakia", "斯洛伐克"));
        SourceDateList.add(new Country("148", "Slovenia", "斯洛文尼亚"));
        SourceDateList.add(new Country("149", "Sudan", "苏丹"));
        SourceDateList.add(new Country("150", "Suriname", "苏里南"));
        SourceDateList.add(new Country("151", "Somalia", "索马里"));
        SourceDateList.add(new Country("152", "Solomon Islands", "所罗门群岛"));
        SourceDateList.add(new Country("153", "Tajikistan", "塔吉克斯坦"));
        SourceDateList.add(new Country("154", "Thailand", "泰国"));
        SourceDateList.add(new Country("155", "Tanzania", "坦桑尼亚"));
        SourceDateList.add(new Country("156", "Tunisia", "突尼斯"));
        SourceDateList.add(new Country("157", "Tuvalu", "图瓦卢"));
        SourceDateList.add(new Country("158", "Turkey", "土耳其"));
        SourceDateList.add(new Country("159", "Turkmenistan", "土库曼斯坦"));
        SourceDateList.add(new Country("160", "Vanuatu", "瓦努阿图"));
        SourceDateList.add(new Country("161", "Guatemala", "危地马拉"));
        SourceDateList.add(new Country("162", "Venezuela", "委内瑞拉"));
        SourceDateList.add(new Country("163", "Brunei Darussalam", "文莱"));
        SourceDateList.add(new Country("164", "Uganda", "乌干达"));
        SourceDateList.add(new Country("165", "Ukraine", "乌克兰"));
        SourceDateList.add(new Country("166", "Uruguay", "乌拉圭"));
        SourceDateList.add(new Country("167", "Uzbekistan", "乌兹别克斯坦"));
        SourceDateList.add(new Country("168", "Spain ", "西班牙"));
        SourceDateList.add(new Country("169", "Western Samoa", "西萨摩亚"));
        SourceDateList.add(new Country("170", "Greece", "希腊"));
        SourceDateList.add(new Country("171", "Hong Kong", "中国 香港"));
        SourceDateList.add(new Country("172", "Singapore", "新加坡"));
        SourceDateList.add(new Country("173", "New Zealand", "新西兰"));
        SourceDateList.add(new Country("174", "Hungary", "匈牙利"));
        SourceDateList.add(new Country("175", "Syria", "叙利亚"));
        SourceDateList.add(new Country("176", "Jamaica", "牙买加"));
        SourceDateList.add(new Country("177", "Armenia", "亚美尼亚"));
        SourceDateList.add(new Country("178", "Yemen", "也门"));
        SourceDateList.add(new Country("179", "Iraq", "伊拉克"));
        SourceDateList.add(new Country("180", "Iran", "伊朗"));
        SourceDateList.add(new Country("181", "Israel", "以色列"));
        SourceDateList.add(new Country("182", "Italy", "意大利"));
        SourceDateList.add(new Country("183", "India", "印度"));
        SourceDateList.add(new Country("184", "Indonesia", "印度尼西亚"));
        SourceDateList.add(new Country("185", "United Kingdom", "英国"));
        SourceDateList.add(new Country("186", "Jordan", "约旦"));
        SourceDateList.add(new Country("187", "Viet Nam", "越南"));
        SourceDateList.add(new Country("188", "Zambia", "赞比亚"));
        SourceDateList.add(new Country("189", "Chad", "乍得"));
        SourceDateList.add(new Country("190", "Chile", "智利"));
        SourceDateList.add(new Country("191", "Central Africa ", "中非"));





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
        int countryCode = sortListView.getSelectedItemPosition();
        String username = getIntent().getStringExtra(Argument.USERNAME);
        String password = getIntent().getStringExtra(Argument.PASSWORD);
        NavHelper.toIdentityPage(this, username, password, countryCode);
    }

    private void toLoginPage(){
        NavHelper.toLoginPage(this);
        finish();
    }
}
