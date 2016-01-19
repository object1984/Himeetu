package com.himeetu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class ErrorView extends RelativeLayout implements OnClickListener {
    private IErrorViewListener listener;

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ErrorView(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.clickRefresh();
        }
    }

    public interface IErrorViewListener {
        public void clickRefresh();
    }

    public IErrorViewListener getListener() {
        return listener;
    }

    public void setListener(IErrorViewListener listener) {
        this.listener = listener;
    }
}
