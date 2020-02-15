package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.BottomNavigationDemoLibraryLiveFragmentBinding;
import com.jacob.book.material.example.adapter.GrammyLibraryLiveAdapter;
import com.jacob.book.material.example.model.Thrones;
import com.jacob.book.material.widgets.GridLayoutVertialItemDecoration;

import java.util.List;

public class BottomNavigationDemoLibraryLiveFragment extends TabBaseFragment {
    private BottomNavigationDemoLibraryLiveFragmentBinding binding;

    public BottomNavigationDemoLibraryLiveFragment(){
        super("直播");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_library_live_fragment, container, false);
        initLiveList();
        return binding.getRoot();
    }

    private void initLiveList(){
        List<Thrones> list = JsonUtils.loadThrones(getResources());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_08 = WidgetsUtils.dpToPx(getContext(), 8);
        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_16, px_16, px_08, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        GrammyLibraryLiveAdapter adapter = new GrammyLibraryLiveAdapter(list);
        View header = View.inflate(this.getContext(), R.layout.grammy_library_header, null);
        adapter.setHeaderView(header);

        binding.recyclerView.setAdapter(adapter);
    }


}
