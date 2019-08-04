package com.mrbell.photoalbum_retrofit2.utility;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class AppUtils {

    public static int dpToPx(int dp, Context mContext) {
        Resources r = mContext.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
