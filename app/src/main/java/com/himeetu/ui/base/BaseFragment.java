package com.himeetu.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;


public class BaseFragment extends Fragment implements View.OnClickListener {
    protected View rootView;
    private TextView titileTextView;
    private View serviceToolbarView;
    private View backView;
    protected boolean isCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    protected void init() {
        loadViews();
        initViews();
        setupListeners();
    }

    protected void loadViews() {
//        titileTextView = (TextView) rootView.findViewById(R.id.text_toolbar_title);
//        serviceToolbarView = rootView.findViewById(R.id.btn_toolbar_service);
//        backView = rootView.findViewById(R.id.btn_toolbar_left);
//        loadingView = rootView.findViewById(R.id.loading_view);
    }

    ;

    protected void initViews() {

    }

    ;

    protected void setupListeners() {
        if (backView != null) {
            backView.setOnClickListener(this);
        }
        if (serviceToolbarView != null) {
            serviceToolbarView.setOnClickListener(this);
        }

    }

    private View loadingView;


    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_toolbar_left:
//                LogUtil.logd("onClick", "R.id.btn_toolbar_left");
//                getActivity().getSupportFragmentManager().popBackStack();
//                break;
//            case R.id.btn_toolbar_service:
//                LogUtil.logd("onClick", "R.id.btn_toolbar_service");
//
//                break;
//        }
    }


    public void setTitle(String title) {
        if (titileTextView != null) {
            titileTextView.setText(title);
        }
    }

    protected void loading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
        disableClick();
    }

    protected void disLoading() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        enableClick();
    }




    public void disableClick() {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void enableClick() {
        if (getActivity() != null) {
            if (getActivity().getWindow() != null) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        isCancel = true;
    }

    @Override
    public void onResume() {
        super.onResume();
//        StatService.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        StatService.onPause(this);
    }
}
