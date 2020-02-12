package com.jacob.book.material.main.model;

import com.chad.library.adapter.base.entity.node.BaseNode;

import java.util.List;

public class ExampleItem extends BaseNode {
    private int id;
    private String title;
    private String path;

    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
