/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-14 下午1:28
 *
 */

package com.jacob.utils;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jacob.material.R;
import com.jacob.material.example.model.Book;
import com.jacob.material.example.model.Commodit;
import com.jacob.material.example.model.ExampleImage;
import com.jacob.material.example.model.GeneralData;
import com.jacob.material.example.model.Grammy;
import com.jacob.material.example.model.GrammySinger;
import com.jacob.material.example.model.Mail;
import com.jacob.material.example.model.MarvelNews;
import com.jacob.material.example.model.Thrones;
import com.jacob.material.main.model.ExampleGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<ExampleGroup> loadExampleGroup(Resources resources) {
        String jsonString = readJson(resources, R.raw.main_example_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ExampleGroup>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<ExampleImage> loadExampleImage(Resources resources) {
        String jsonString = readJson(resources, R.raw.example_image_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<ExampleImage>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<MarvelNews> loadMarvelNews(Resources resources) {
        String jsonString = readJson(resources, R.raw.marvel_news_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<MarvelNews>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<Mail> loadMails(Resources resources) {
        String jsonString = readJson(resources, R.raw.mail_in_box_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Mail>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<Grammy> loadGrammy(Resources resources) {
        String jsonString = readJson(resources, R.raw.grammy_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Grammy>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<GrammySinger> loadGrammySinger(Resources resources) {
        String jsonString = readJson(resources, R.raw.grammy_singer_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<GrammySinger>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<Thrones> loadThrones(Resources resources) {
        String jsonString = readJson(resources, R.raw.thrones_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Thrones>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<Book> loadBooks(Resources resources) {
        String jsonString = readJson(resources, R.raw.book_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<Commodit> loadCommodits(Resources resources) {
        String jsonString = readJson(resources, R.raw.commodit_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Commodit>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

    public static List<GeneralData> loadGeneralDatas(Resources resources) {
        String jsonString = readJson(resources, R.raw.general_list);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<GeneralData>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }


    public static String readJson(Resources resources, int rawResId) {
        String result = null;
        InputStream inputStream = resources.openRawResource(rawResId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int len = reader.read(buffer);
            while (len != -1) {
                writer.write(buffer, 0, len);
                len = reader.read(buffer);
            }
        } catch (IOException exception) {
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
            }
        }
        result = writer.toString();
        return result;
    }

}
