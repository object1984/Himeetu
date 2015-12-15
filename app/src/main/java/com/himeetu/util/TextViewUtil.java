package com.himeetu.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

/**
 * Created by object1984 on 2015/5/26.
 */
public class TextViewUtil {
    /**
     * 下划线
     */
    public static void addUnderLineSpan(TextView textView, String str) {
        if (textView == null) {
            return;
        }

        SpannableString spanString = new SpannableString(str);
        UnderlineSpan span = new UnderlineSpan();
        spanString.setSpan(span, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(spanString);
    }
}
