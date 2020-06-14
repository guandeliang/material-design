package com.jacob.material.elegantlaunch

enum class DateFormatMode(var mode: String) {
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
}
