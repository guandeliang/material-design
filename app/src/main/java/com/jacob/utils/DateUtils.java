package com.jacob.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static String formatMoney(double money) {
        DecimalFormat df= new DecimalFormat("###,##0.00");
        return df.format(money);
    }

    public static String formatAccountId(String id) {
        return "******" + id.substring(id.length() - 4);
    }

    public enum DateFormatMode{
        ONLY_YM("yyyyMM"),
        SIMPLE_YMD("yyyy-MM-dd"),
        SIMPLE_MD("MM-dd"),
        SIMPLE_YMD_HM("yyyy-MM-dd HH:mm"),
        SIMPLE_YMD_HMS("yyyy-MM-dd HH:mm:ss"),
        SIMPLE_YMD_HMS_S("yyyy-MM-dd HH:mm:ss SSS"),
        SIMPLE_YMD_W("yyyy-MM-dd E"),
        SIMPLE_HM("HH:mm"),
        JDK_YMD_HMS("yyyy-MM-ddTHH:mm:ss"),
        CHINA_YMD("yyyy年MM月dd日"),
        CHINA_YMD_HMS("yyyy年MM月dd日 HH时mm分ss秒"),
        CHINA_MD_HM("MM月dd日 HH:mm"),
        CHINA_YMD_W("yyyy年MM月dd日 E");

        public String mode;
        DateFormatMode(String mode) {
            this.mode = mode;
        }
    }

    public static String formatDate(Date value, DateFormatMode mode){
        String result = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(mode.mode, Locale.CHINA);
            result = format.format(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static Date monthFirstDay(Date value){
        Calendar cal  = Calendar.getInstance();
        cal.setTime(value);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }


    public static Date nextMonthFirstDay(Date value){
        Calendar cal  = Calendar.getInstance();
        cal.setTime(value);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    public static void setImageViewResourceColor(Activity activity, ImageView imageView, int imageResourceId, int colorResourceId){
        Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(activity, imageResourceId));
        imageView.setImageDrawable(drawable);

        int color = -1;

        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(colorResourceId, typedValue, true);
        if (typedValue.resourceId != 0) {
            color = ContextCompat.getColor(activity, typedValue.resourceId);
        } else {
            color = ContextCompat.getColor(activity, colorResourceId);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
        }
    }

}
