package com.jacob.http

import com.jacob.material.applaunch.Thrones
import io.reactivex.Observable
import retrofit2.http.GET

interface HttpLoader {
    @GET("json/thrones_list.json")
    fun getThrones(): Observable<List<Thrones>>
}