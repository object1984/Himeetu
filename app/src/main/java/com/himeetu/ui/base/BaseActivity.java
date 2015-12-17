package com.himeetu.ui.base;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.himeetu.R;
import com.himeetu.app.NavHelper;
//测试
public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ClipboardManager clipboardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavHelper.finishWithAnim(this);
    }

    protected void copyToClipboardManager(String text){
        ClipData clipData = ClipData.newPlainText("msg", text);
        clipboardManager.setPrimaryClip(clipData);
    }
    protected void init() {
        loadViews();
        initViews();
        setupListeners();
    }

    protected void loadViews() {
    }


    protected void initViews() {

    }


    protected void setupListeners() {

    }

    ;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View leftView;
    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int RESULT_OK = 10;
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int RESIZE_REQUEST_CODE = 2;

    public void setupToolbar() {
        View v = findViewById(R.id.toolbar);
        if (v != null) {
            setTitle("");
            toolbar = (Toolbar) v;
            setSupportActionBar(toolbar);
            toolbarTitle = (TextView) v.findViewById(R.id.toolbar_title);
            if (toolbarTitle != null) {
                getSupportActionBar().setDisplayShowTitleEnabled(false);
            }

            leftView = v.findViewById(R.id.toolbar_back);

            if (leftView != null) {
                leftView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NavHelper.finishWithAnim(BaseActivity.this);
                        onBackPressed();
                    }
                });
            }

        }
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }


    private ProgressDialog getLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(BaseActivity.this);
            progressDialog.setTitle("notice");
            progressDialog.setMessage("please wait...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
        }

        return progressDialog;
    }

    public void showLoading() {
        getLoading().show();
    }

    public void hideLoading() {
        getLoading().dismiss();
    }

//    protected void setToolbarTitle(String title) {
//        TextView toolbarTitleTextView = (TextView) findViewById(R.id.toolbar_title);
//        if (toolbarTitleTextView != null) {
//            toolbarTitleTextView.setText(title);
//        }
//    }


    private Fragment retrieveFromCache(String name) {
        //从fragmentManager中获取已有的fragment对象
        for (Fragment backFragment : getSupportFragmentManager().getFragments()) {
            if (null != backFragment) {
                if (backFragment.getTag().equals(name))
                    return backFragment;
            }
        }
        return null;
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    protected void setTitleText(int resId){
        TextView titleTextView = (TextView)findViewById(R.id.text_title);

        if(titleTextView != null){
            titleTextView.setText(resId);
        }
    }

    protected void setThemeTranslucent(){
        if (android.os.Build.VERSION.SDK_INT > 18) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
