package com.jacob.http

import com.jacob.material.elegantlaunch.Thrones
import retrofit2.Response
import retrofit2.http.GET

interface HttpLoader {
    @GET("json/thrones_list.json")
    suspend fun getThrones(): Response<List<Thrones>>
}