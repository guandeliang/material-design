/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-1 下午6:03
 *
 */

package com.jacob.material.base;

public class TabItemContent {
    private String title;
    private int iconRecId;

    public TabItemContent(String title, int iconRecId){
        this.title = title;
        this.iconRecId = iconRecId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconRecId() {
        return iconRecId;
    }

    public void setIconRecId(int iconRecId) {
        this.iconRecId = iconRecId;
    }
}
