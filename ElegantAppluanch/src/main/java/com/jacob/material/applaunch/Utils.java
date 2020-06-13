package com.jacob.material.applaunch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Thrones> loadThrones(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Thrones>>(){}.getType();
        return gson.fromJson(jsonString, listType);
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


}
