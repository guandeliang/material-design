package com.jacob.http;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jacob.utils.DateUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MdRetrofitFactory {
    private static String BASE_URL = "https://guandeliang.github.io/material-design/web/json/";
    private static Retrofit createRetrofit(){

        OkHttpClient okHttpClient = MdOkHttpClientService.getOkHttpClient();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat(DateUtils.DateFormatMode.SIMPLE_YMD.mode)
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static <T> T getLoader(Class<T> loaderClass){
        Retrofit retrofit = MdRetrofitFactory.createRetrofit();
        return retrofit.create(loaderClass);
    }

}

