package com.faruk.retrofitshimmer.utility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/**
 * Created by farukhossain on 2019/04/25.
 */

public class Utils {

    public static Drawable getProgressBarIndeterminate(Context context) {
        final int[] attrs = {android.R.attr.indeterminateDrawable};
        final int attrs_indeterminateDrawable_index = 0;
        TypedArray a = context.obtainStyledAttributes(android.R.style.Widget_ProgressBar, attrs);
        try {
            return a.getDrawable(attrs_indeterminateDrawable_index);
        } finally {
            a.recycle();
        }
    }
}
