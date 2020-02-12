package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.BottomNavigationDemoLibraryArtistFragmentBinding;
import com.jacob.book.material.example.adapter.GrammyLibraryArtistAdapter;
import com.jacob.book.material.example.model.GrammySinger;
import com.jacob.book.material.widgets.VertialLinearLayoutItemDecoration;

import java.util.List;

public class BottomNavigationDemoLibraryArtistFragment extends TabBaseFragment {
    private BottomNavigationDemoLibraryArtistFragmentBinding binding;

    public BottomNavigationDemoLibraryArtistFragment(){
        super("歌手");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_library_artist_fragment, container, false);
        initLibraryList();
        return binding.getRoot();
    }

    private void initLibraryList(){
        List<GrammySinger> list = JsonUtils.loadGrammySinger(getResources());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_08 = WidgetsUtils.dpToPx(getContext(), 8);
        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);

        VertialLinearLayoutItemDecoration decoration = new VertialLinearLayoutItemDecoration(px_16, px_16, px_16, px_16, px_08);
        binding.recyclerView.addItemDecoration(decoration);


        GrammyLibraryArtistAdapter adapter = new GrammyLibraryArtistAdapter(list);
        binding.recyclerView.setAdapter(adapter);
    }


}
