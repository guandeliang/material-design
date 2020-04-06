/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-10 下午7:06
 *
 */

package com.jacob.material.example.model;

import androidx.annotation.Nullable;

public class GrammySinger {
    private int id;
    private String fileName;
    private String title;
    private String subTitle;
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj == null){
            return false;
        }
        if(!(obj instanceof GrammySinger)){
            return false;
        }

        GrammySinger o = (GrammySinger)obj;
        if(o.getId() == this.getId()){
            return true;
        }else{
            return false;
        }
    }
}
