/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午10:24
 *
 */

package com.jacob.book;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

public class WidgetsUtils {
    public static int dpToPx(Context context, float dps){
        return Math.round(context.getResources().getDisplayMetrics().density * dps);
    }


    public static Drawable getColorDrawable(Activity activity, int imageResourceId, int colorResourceId){
        int color = getColorValue(activity, colorResourceId);
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(activity.getApplicationContext(), imageResourceId));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
        return drawable;
    }

    public static void setDrawableColor(Context context, Drawable drawable, int colorResourceId){
        int color = getColorValue(context, colorResourceId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

    public static int getColorValue(Context context, int colorResourceId){
        int color = -1;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(colorResourceId, typedValue, true);
        if (typedValue.resourceId != 0) {
            color = ContextCompat.getColor(context, typedValue.resourceId);
        } else {
            color = ContextCompat.getColor(context, colorResourceId);
        }
        return color;
    }

    public static void setSystemBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(color));
        }
    }

    public static void setNavigationBarColor(Activity act, @ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = act.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setNavigationBarColor(act.getResources().getColor(color));
        }
    }

    public static void setSystemBarLight(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            //flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

            flags = flags|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }

    public static void setSystemBarDark(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View view = act.findViewById(android.R.id.content);
            int flags = view.getSystemUiVisibility();
            flags = flags & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
        }
    }



}
