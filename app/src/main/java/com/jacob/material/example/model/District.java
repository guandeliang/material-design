package com.jacob.material.example.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class District implements Serializable {
    private String code;
    private String title;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        return this.title;
    }
}
