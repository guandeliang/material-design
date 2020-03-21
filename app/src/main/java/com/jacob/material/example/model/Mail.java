/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-28 下午12:23
 *
 */

package com.jacob.material.example.model;

import java.io.Serializable;

public class Mail implements Serializable {
    private int id;
    private String title;
    private String names;
    private String headerFile;
    private String summary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getHeaderFile() {
        return headerFile;
    }

    public void setHeaderFile(String headerFile) {
        this.headerFile = headerFile;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
