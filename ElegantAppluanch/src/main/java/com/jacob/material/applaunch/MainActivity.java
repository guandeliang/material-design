package com.jacob.material.applaunch;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.http.HttpLoader;
import com.jacob.http.MdRetrofitFactory;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ThronesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_activity);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ThronesAdapter(this);
        recyclerView.setAdapter(adapter);

        loadThrones();
    }

    private void loadThrones(){

        MdRetrofitFactory.getLoader(HttpLoader.class).getThrones()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<List<Thrones>>(){
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("MATERIAL_DESIGN", "onSubscribe");
                    }

                    @Override
                    public void onNext(List<Thrones> list) {
                        Log.d("MATERIAL_DESIGN", "onNext list size = " + list.size());
                        adapter.setDataList(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("MATERIAL_DESIGN", "onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d("MATERIAL_DESIGN", "onComplete");
                    }
                });
    }

}