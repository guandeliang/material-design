/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-10 下午6:07
 *
 */

package com.jacob.book.material.example.model;

public class MarvelNews {
    private int id;
    private String mediaGroup;
    private String logoGroup;
    private String genTime;
    private String title;
    private String imageFile;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaGroup() {
        return mediaGroup;
    }

    public void setMediaGroup(String mediaGroup) {
        this.mediaGroup = mediaGroup;
    }

    public String getLogoGroup() {
        return logoGroup;
    }

    public void setLogoGroup(String logoGroup) {
        this.logoGroup = logoGroup;
    }

    public String getGenTime() {
        return genTime;
    }

    public void setGenTime(String genTime) {
        this.genTime = genTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
