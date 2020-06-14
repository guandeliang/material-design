/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.banner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.http.MdRetrofitFactory;
import com.jacob.material.R;
import com.jacob.material.databinding.BannerNetworkOnlineFragmentBinding;
import com.jacob.material.example.adapter.BannnerNetworkItemAdapter;
import com.jacob.material.example.httploader.HttpLoader;
import com.jacob.material.example.model.Thrones;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BannerNetworkOnlineFragment extends Fragment implements LifecycleObserver {
    private BannerNetworkOnlineFragmentBinding binding;
    private BannnerNetworkItemAdapter adapter;

    public BannerNetworkOnlineFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_network_online_fragment, container, false);

        List<Thrones> list = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);
        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(0, px_16, 0);
        binding.recyclerView.addItemDecoration(decoration);

        adapter = new BannnerNetworkItemAdapter(list);
        binding.recyclerView.setAdapter(adapter);

        loadThrones();

        return binding.getRoot();

    }


    private void loadThrones(){

        MdRetrofitFactory.getLoader(HttpLoader.class).getThrones()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<List<Thrones>>(){
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(List<Thrones> list) {
                        adapter.setNewData(list);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {
                        binding.shimmerFrameLayout.stopShimmer();
                        binding.shimmerFrameLayout.setVisibility(View.GONE);
                    }
                });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onLifecycleResume() {
        binding.shimmerFrameLayout.startShimmer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onLifecyclePause() {
        binding.shimmerFrameLayout.stopShimmer();
    }
}
