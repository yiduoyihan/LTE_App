package com.leidi.lteapp.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.EditText;

public class EditTextUtils {

    /**
     * ========================================================
     * 设置EditText的hint字体的大小
     * ========================================================
     */
    public static void setEditTextHintSize(EditText editText, String hintText, int size) {
        //定义hint的值
        SpannableString ss = new SpannableString(hintText);
        //设置字体大小 true表示单位是sp
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(size, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        editText.setHint(new SpannedString(ss));
    }
}
