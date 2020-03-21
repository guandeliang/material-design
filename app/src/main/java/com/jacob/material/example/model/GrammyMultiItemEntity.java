/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-9 下午8:54
 *
 */

package com.jacob.material.example.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class GrammyMultiItemEntity implements MultiItemEntity {
    public static int ITEM_TYPE_A = 1;
    public static int ITEM_TYPE_B = 2;
    public static int ITEM_TYPE_C = 3;

    private int itemType;

    public GrammyMultiItemEntity(int itemType){
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
