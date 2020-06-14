package com.jacob.http

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jacob.material.applaunch.DateFormatMode
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MdRetrofitFactory {
    var BASE_URL = "https://guandeliang.github.io/material-design/web/"
    private fun createRetrofit(): Retrofit {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setDateFormat(DateFormatMode.SIMPLE_YMD.mode)
                .create()
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(MdOkHttpClient.client!!)
                .build()
    }

    fun <T> getLoader(loaderClass: Class<T>): T {
        val retrofit = createRetrofit()
        return retrofit.create(loaderClass)
    }
}