/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午10:32
 *
 */

package com.jacob.material.main.model;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import java.util.ArrayList;
import java.util.List;

public class ExampleGroup extends BaseExpandNode {
    private int id;
    private String iconName;
    private String title;
    private List<ExampleItem> itemList;
    private List<BaseNode> childNode;

    public ExampleGroup(){
        setExpanded(false);
    }

    @Override
    public List<BaseNode> getChildNode() {
        if(childNode == null){
            childNode = new ArrayList<>();
        }
        childNode.clear();
        childNode.addAll(itemList);
        return childNode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ExampleItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ExampleItem> itemList) {
        this.itemList = itemList;
    }
}
