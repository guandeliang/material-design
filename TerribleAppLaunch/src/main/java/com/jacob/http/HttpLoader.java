package com.jacob.http;


import com.jacob.material.applaunch.Thrones;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpLoader {
    @GET("json/thrones_list.json")
    Observable<List<Thrones>> getThrones();
}
