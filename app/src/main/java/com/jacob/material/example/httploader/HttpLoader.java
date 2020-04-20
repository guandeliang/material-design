package com.jacob.material.example.httploader;

import com.jacob.material.example.model.Thrones;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpLoader {
    @GET("json/thrones_list.json")
    Observable<List<Thrones>> getThrones();
}
