package com.jacob.book.material.example.bottomappbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomAppBarDemoMailContentFragmentBinding;
import com.jacob.book.material.example.model.Mail;
import com.jacob.book.temp.TempConstant;

public class BottomAppBarDemoMailContentFragment extends Fragment implements LifecycleObserver {
    public static String PARAM_MAIL_ITEM = "PARAM_MAIL_ITEM";

    private BottomAppBarDemoMailContentFragmentBinding binding;
    private BottomAppBarDemoViewModel viewModel;
    private Mail mail;

    public BottomAppBarDemoMailContentFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_app_bar_demo_mail_content_fragment, container, false);
        mail = (Mail)getArguments().getSerializable(PARAM_MAIL_ITEM);
        viewModel = new ViewModelProvider(getActivity()).get(BottomAppBarDemoViewModel.class);

        binding.headerImageView.setTransitionName("transition_" + mail.getId());
        int headerResId = getContext().getResources().getIdentifier(mail.getHeaderFile(), "drawable", getContext().getPackageName());
        binding.headerImageView.setImageResource(headerResId);


        Log.d(TempConstant.LOG_TAG, mail.getTitle());


        binding.backImageView.setOnClickListener(new OnBackImageClickListener());

        return binding.getRoot();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onLifecycleStart(){
        viewModel.getFragmentLiveData().postValue(BottomAppBarDemoViewModel.FragmentEnum.MAIL_CONTENT);
    }


    private class OnBackImageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(BottomAppBarDemoMailContentFragment.this.getActivity(), R.id.nav_host_fragment).popBackStack();
        }
    }

}
