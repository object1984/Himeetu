package com.himeetu.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.himeetu.R;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.DateUtils;

import java.util.Date;

/**
 * Created by object1984 on 15/12/17.
 */
public class IdentityActivity extends BaseActivity implements View.OnClickListener {
    private TextView birthdayTextView;
    private TimePickerView birthdayTimePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_identity);
        init();
    }

    @Override
    protected void loadViews() {
        super.loadViews();
        birthdayTextView = (TextView) findViewById(R.id.text_birthday);

        birthdayTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        birthdayTimePickerView.setCyclic(false);
        birthdayTimePickerView.setRange(1920, 2020);
        birthdayTimePickerView.setTime(new Date());
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        birthdayTextView.setOnClickListener(this);

        birthdayTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                birthdayTextView.setText(DateUtils.format(date));
            }
        });
    }

    @Override
    protected void initViews() {
        super.initViews();
//        setTitleText(R.string.your_identity);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.text_birthday:
             birthdayTimePickerView.show();
             break;
     }
    }
}
