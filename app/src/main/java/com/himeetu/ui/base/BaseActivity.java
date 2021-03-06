package com.himeetu.ui.base;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
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
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        NavHelper.finishWithAnim(this);
    }

    protected void copyToClipboardManager(String text) {
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

    private TextView toolbarTitle;
    private View backView;

    public void setupToolbar(boolean showBackbtn, @NonNull int titleResId) {
        View v = findViewById(R.id.toolbar);
        if (v != null) {
            toolbarTitle = (TextView) v.findViewById(R.id.toolbar_title);
            if (toolbarTitle != null && titleResId != 0) {
                toolbarTitle.setText(titleResId);
            }

            backView = v.findViewById(R.id.toolbar_back);
            if (backView != null) {
                if (showBackbtn) {
                    backView.setVisibility(View.VISIBLE);
                    backView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavHelper.finishWithAnim(BaseActivity.this);
                            onBackPressed();
                        }
                    });
                } else {
                    backView.setVisibility(View.GONE);
                    backView.setOnClickListener(null);
                }

            }

        }
    }

    public void setupToolbar(boolean showBackbtn, @NonNull String title) {
        View v = findViewById(R.id.toolbar);
        if (v != null) {
            toolbarTitle = (TextView) v.findViewById(R.id.toolbar_title);
            if (toolbarTitle != null) {
                toolbarTitle.setText(title);
            }

            backView = v.findViewById(R.id.toolbar_back);
            if (backView != null) {
                if (showBackbtn) {
                    backView.setVisibility(View.VISIBLE);
                    backView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            NavHelper.finishWithAnim(BaseActivity.this);
                            onBackPressed();
                        }
                    });
                } else {
                    backView.setVisibility(View.GONE);
                    backView.setOnClickListener(null);
                }

            }

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

    public final void setToolbarTitle(String title) {
        TextView toolbarTitleTextView = (TextView) findViewById(R.id.toolbar_title);
        if (toolbarTitleTextView != null) {
            toolbarTitleTextView.setText(title);
        }
    }


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

    public final  void setToolBarTitleColor(int resId) {
        TextView rightTextView = (TextView) findViewById(R.id.toolbar_title);

        if (rightTextView != null) {
            rightTextView.setTextColor(resId);
        }
    }

    public final void setRightTextColor(int resId) {
        TextView rightTextView = (TextView) findViewById(R.id.toolbar_right_text);

        if (rightTextView != null) {
            rightTextView.setTextColor(resId);
        }
    }


    protected void setRightTextAndVisible(int resId, int visible) {
        TextView rightTextView = (TextView) findViewById(R.id.toolbar_right_text);

        if (rightTextView != null) {
            rightTextView.setText(resId);
        }
        rightTextView.setVisibility(visible);
    }

    protected  String getRightText(){
        TextView rightTextView = (TextView) findViewById(R.id.toolbar_right_text);

        if (rightTextView != null) {
            return  rightTextView.getText().toString();
        }

        return  null;
    }

    protected void setRightOnClickListener(View.OnClickListener onClickListener) {
        TextView rightTextView = (TextView) findViewById(R.id.toolbar_right_text);

        if (rightTextView != null) {
            rightTextView.setOnClickListener(onClickListener);
        }
    }

    public final void setToolBarColor(int resId) {
        View toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setBackgroundColor(resId);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public final void setThemeTranslucent() {
        if (android.os.Build.VERSION.SDK_INT > 18) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected void setStatusBarColor(@NonNull int colorResId){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(colorResId));
        }
        StatusBarCompat.compat(this, getResources().getColor(colorResId));
    }


}
