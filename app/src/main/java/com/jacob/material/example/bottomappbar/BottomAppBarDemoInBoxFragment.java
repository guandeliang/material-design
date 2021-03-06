/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-16 上午10:04
 *
 */

package com.jacob.material.example.bottomappbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jacob.utils.JsonUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarDemoInBoxFragmentBinding;
import com.jacob.material.example.adapter.MailInBoxAdapter;
import com.jacob.material.example.model.Mail;

import java.util.List;

public class BottomAppBarDemoInBoxFragment extends Fragment implements LifecycleObserver {
    private BottomAppBarDemoInBoxFragmentBinding binding;
    private BottomAppBarDemoViewModel viewModel;
    private PopupMenu filterMenu;

    public BottomAppBarDemoInBoxFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_app_bar_demo_in_box_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(BottomAppBarDemoViewModel.class);

        //设置过滤菜单
        filterMenu = new PopupMenu(getActivity(), binding.filterImageView);
        filterMenu.getMenuInflater().inflate(R.menu.bottom_app_bar_demo_in_box_filter_menu, filterMenu.getMenu());
        filterMenu.setOnMenuItemClickListener(new OnFilterMenuItemClickListener());
        binding.filterImageView.setOnClickListener(new OnFilterIconClickListener());

        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), GridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        List<Mail> mailList = JsonUtils.loadMails(getResources());
        MailInBoxAdapter adapter = new MailInBoxAdapter(mailList);

        View header = View.inflate(this.getActivity(), R.layout.example_image_header, null);
        TextView headerTextView = header.findViewById(R.id.text_view);
        headerTextView.setText("今天");
        adapter.setHeaderView(header);

        adapter.setOnItemClickListener(new OnMailItemClickListener());

        binding.recyclerView.setAdapter(adapter);


        return binding.getRoot();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onLifecycleStart(){
        viewModel.getFragmentLiveData().postValue(BottomAppBarDemoViewModel.FragmentEnum.IN_BOX);
    }

    private class OnFilterIconClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            filterMenu.show();
        }
    }

    private class OnFilterMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg = null;
            switch (item.getItemId()) {
                case R.id.action_not_read: {
                    msg = "只显示没有阅读过的邮件";
                    break;
                }
                case R.id.action_red_flag: {
                    msg = "只显示重点标注过的邮件";
                    break;
                }
                case R.id.action_has_append: {
                    msg = "只显示带有附件的邮件";
                    break;
                }
                case R.id.action_at_me: {
                    msg = "只显示@我的邮件";
                    break;
                }
                default: {
                    msg = "这个是什么东西";
                }
            }
            viewModel.getActionMsgLiveData().postValue(msg);
            return true;
        }
    }

    private class OnMailItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            MailInBoxAdapter mailInBoxAdapter = (MailInBoxAdapter)adapter;
            Mail mail = mailInBoxAdapter.getData().get(position);
            bundle.putSerializable(BottomAppBarDemoMailContentFragment.PARAM_MAIL_ITEM, mail);
            Navigation.findNavController(getActivity(),  R.id.nav_host_fragment).navigate(R.id.show_mail_content, bundle);
        }
    }

}
