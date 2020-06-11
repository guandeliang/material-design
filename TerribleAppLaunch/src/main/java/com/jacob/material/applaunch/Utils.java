package com.jacob.material.applaunch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Thrones> loadThrones(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Thrones>>(){}.getType();
        return gson.fromJson(jsonString, listType);
    }

}
