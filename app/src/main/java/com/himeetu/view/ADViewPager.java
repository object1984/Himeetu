package com.himeetu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.himeetu.adapter.AdAdapter;
import com.himeetu.model.Ad;
import com.himeetu.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by object1984 on 16/1/19.
 */
public class ADViewPager extends ViewPager{
    private AdAdapter mAdAdapter;
    private Context context;
    private Paint paint;

    public ADViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawCycle(canvas);
    }

    private void drawCycle(Canvas canvas) {
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        int count = 0;
        if (this.getAdapter() != null) {
            count = this.getAdapter().getCount();
        }
        int select = getCurrentItem();
        float density = getContext().getResources().getDisplayMetrics().density;
        int itemWidth = (int) (11 * density);
        int itemHeight = itemWidth / 2;
        int x = getWidth() - count * itemWidth - DensityUtil.dip2px(context, 10);
        int y = getHeight() - itemWidth;
        int minItemHeight = (int) ((float) itemHeight * 0.8F);
        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < count; i++) {
            if (select == i) {
                paint.setColor(0xFF5252FF);
                canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
            } else {
                paint.setColor(0xFFe6e6e6);
                canvas.drawCircle(x + itemWidth * i + itemWidth / 2, y, minItemHeight, paint);
            }
        }
        canvas.restore();
    }
}
