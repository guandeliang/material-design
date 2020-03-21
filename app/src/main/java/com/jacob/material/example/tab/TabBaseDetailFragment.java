/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-24 下午8:43
 *
 */

package com.jacob.material.example.tab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TabBaseDetailFragmentBinding;
import com.jacob.material.example.adapter.BookDetailAdapter;
import com.jacob.material.example.model.Book;
import com.jacob.material.widgets.ViewPager2BottomSheetBehavior;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

public class TabBaseDetailFragment extends Fragment implements LifecycleObserver {
    public static String PARAM_BOOK = "PARAM_BOOK";
    private TabBaseDetailFragmentBinding binding;
    private ViewPager2BottomSheetBehavior sheetBehavior;
    private int mutedColor;

    public TabBaseDetailFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_detail_fragment, container, false);
        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());
        binding.nestedScrollView.setOnScrollChangeListener(new OnContentScrollChangeListener());

        intBottomSheet();
        setData();

        return binding.getRoot();
    }

    private void intBottomSheet(){
        sheetBehavior = ViewPager2BottomSheetBehavior.from(binding.bottomSheetLinearLayout);
        sheetBehavior.setFitToContents(false);
        sheetBehavior.setPeekHeight(WidgetsUtils.dpToPx(this.getContext(), 56));
        sheetBehavior.setState(ViewPager2BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.addBottomSheetCallback(new SheetStateChangeCallback());


        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setTopLeftCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this.getContext(), 36));
        MaterialShapeDrawable drawable = new MaterialShapeDrawable(builder.build());
        drawable.setTint(WidgetsUtils.getColorValue(this.getActivity(), android.R.attr.colorPrimary));
        binding.bottomSheetTitleLinearLayout.setBackground(drawable);


        binding.viewPager.registerOnPageChangeCallback(new SheetPageChangeCallback());
        BookDetailAdapter adapter = new BookDetailAdapter();
        binding.viewPager.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new MainTabConfigurationStrategy(adapter)
        );
        mediator.attach();

        binding.tabLayout.addOnTabSelectedListener(new TabSelectedListener());


    }

    private class MainTabConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy {
        private BookDetailAdapter adapter;

        public MainTabConfigurationStrategy(BookDetailAdapter adapter){
            this.adapter = adapter;
        }

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.book_detail_tab_item, binding.tabLayout, false);
            TextView titleTextView = view.findViewById(R.id.title_text_view);
            titleTextView.setText(adapter.getItem(position));
            TextView subTitleTextView = view.findViewById(R.id.sub_title_text_view);
            subTitleTextView.setText(adapter.getNumber(position));
            tab.setCustomView(view);
        }
    }



    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            int appBarHeight = WidgetsUtils.dpToPx(getContext(), 56) + statusBarHeight;
            binding.appBarLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, appBarHeight));
            binding.statusBarBackgroundView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            binding.appBarFillView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, appBarHeight));
            sheetBehavior.setExpandedOffset(appBarHeight);//设置BottomSheet最大高度
            binding.expandedOffsetstatusFillView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, appBarHeight));

            return windowInsets;
        }
    }




    //创建异步调色板
    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {

                int defaultMutedColor = WidgetsUtils.getColorValue(getContext(), R.color.dark_color_surface);
                mutedColor = p.getMutedColor(defaultMutedColor);

                binding.coordinatorLayout.setBackgroundColor(mutedColor);
                binding.appBarLayout.setBackgroundColor(calcBackgroundColor(0f));

                int defaultLightVibrantColor = WidgetsUtils.getColorValue(getContext(), R.color.dark_color_primary);
                int lightVibrantColor = p.getLightVibrantColor(defaultLightVibrantColor);
                binding.func001Button.setBackgroundColor(lightVibrantColor);
                binding.func002Button.setBackgroundColor(lightVibrantColor);
                binding.func003Button.setBackgroundColor(lightVibrantColor);



            }
        });
    }

    private static final int BEFORE_ROLL = -1;
    private static final int IN_ROLL = 0;
    private static final int AFTER_ROLL = 1;

    @RestrictTo(LIBRARY_GROUP)
    @IntDef({
            BEFORE_ROLL,
            IN_ROLL,
            AFTER_ROLL
    })

    @Retention(RetentionPolicy.SOURCE)
    public @interface RollState {}


    private @RollState int rollState = BEFORE_ROLL;

    private class OnContentScrollChangeListener implements NestedScrollView.OnScrollChangeListener{
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            int contentTop = binding.bookImageView.getTop();
            int appBarBottom = binding.appBarLayout.getBottom();
            int startChangeDistance = (int)((contentTop - appBarBottom)*0.5f);
            int endChangeDistance = startChangeDistance * 8;

            if(scrollY < startChangeDistance){
                if(BEFORE_ROLL != rollState){
                    if(AFTER_ROLL == rollState || IN_ROLL == rollState) {
                        appBarTitleSlideDown();
                    }

                    binding.appBarLayout.setBackgroundColor(calcBackgroundColor(0f));
                    rollState = BEFORE_ROLL;
                }
            }else if(scrollY >= startChangeDistance && scrollY <= endChangeDistance){
                if(BEFORE_ROLL == rollState){
                    appBarTitleSlideUp();
                }
                rollState = IN_ROLL;
                float transparent = (float)(scrollY - startChangeDistance)/(float)(endChangeDistance - startChangeDistance);
                binding.appBarLayout.setBackgroundColor(calcBackgroundColor(transparent));
            }else{
                if(AFTER_ROLL != rollState){
                    rollState = AFTER_ROLL;
                    binding.appBarLayout.setBackgroundColor(calcBackgroundColor(1f));
                }
            }
        }
    }

    private class SheetStateChangeCallback extends ViewPager2BottomSheetBehavior.BottomSheetCallback{
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(BottomSheetBehavior.STATE_EXPANDED == newState){
                appBarTitleSlideUp();
            }else if(BottomSheetBehavior.STATE_COLLAPSED == newState){
                if(rollState == BEFORE_ROLL){
                    appBarTitleSlideDown();
                }
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    }

    private class SheetPageChangeCallback extends ViewPager2.OnPageChangeCallback {

        @Override
        public void onPageSelected(int position) {
            setSheetBehaviorNestedScrollingChild(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state != ViewPager2.SCROLL_STATE_IDLE){
                return;
            }
            int position = binding.viewPager.getCurrentItem();
            setSheetBehaviorNestedScrollingChild(position);
        }

        private void setSheetBehaviorNestedScrollingChild(int position){
            View child = binding.viewPager.getChildAt(0);
            if(child == null || !(child instanceof RecyclerView)){
                sheetBehavior.setNestedScrollingChild(null);
                return;
            }
            RecyclerView  recyclerView = (RecyclerView)child;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

            if(layoutManager == null){
                sheetBehavior.setNestedScrollingChild(null);
                return;
            }

            View holder = layoutManager.findViewByPosition(position);
            if(holder instanceof NestedScrollView){
                sheetBehavior.setNestedScrollingChild(holder);
            }else{
                sheetBehavior.setNestedScrollingChild(null);
            }
        }
    }


    private class TabSelectedListener implements TabLayout.OnTabSelectedListener{
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            if(ViewPager2BottomSheetBehavior.STATE_COLLAPSED == sheetBehavior.getState()){
                sheetBehavior.setState(ViewPager2BottomSheetBehavior.STATE_EXPANDED);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

    private boolean isAppBarBarTitlleRising = false;

    private void appBarTitleSlideUp(){
        if(isAppBarBarTitlleRising){
            return;
        }
        isAppBarBarTitlleRising = true;
        int genMoveY = 0 - binding.appBarLayout.getHeight() * 2;
        int SpceMoveY = 0 - WidgetsUtils.dpToPx(getContext(), 56);
        binding.barGenTitleTextView.animate().translationY(genMoveY).setDuration(500).start();
        binding.barSpecTitleTextView.animate().translationY(SpceMoveY).setDuration(500).start();
    }

    private void appBarTitleSlideDown(){
        if(!isAppBarBarTitlleRising){
            return;
        }
        isAppBarBarTitlleRising = false;
        binding.barGenTitleTextView.animate().translationY(0).setDuration(500).start();
        binding.barSpecTitleTextView.animate().translationY(0).setDuration(500).start();
    }


    private int calcBackgroundColor(float opacity){
        if(opacity > 1){
            opacity = 1;
        }
        if(opacity < 0){
            opacity = 0;
        }

        int maskColor = WidgetsUtils.getColorValue(getContext(), R.color.transparent_black_30);
        int bgColor = ColorUtils.compositeColors(maskColor, mutedColor);

        int alpha = (int)(255* opacity);
        int red = Color.red(bgColor) ;
        int green = Color.green(bgColor);
        int blue = Color.blue(bgColor);

        int transBgColor = Color.argb(alpha, red, green, blue);
        return transBgColor;
    }


    private void setData(){
        Book book = (Book) getArguments().getSerializable(PARAM_BOOK);
        int bookImageResId = getContext().getResources().getIdentifier(book.getImageFile(), "drawable", getContext().getPackageName());
        binding.bookImageView.setImageResource(bookImageResId);
        binding.titleTextView.setText(book.getTitle());
        binding.barSpecTitleTextView.setText(book.getTitle());
        binding.subTitleTextView.setText(book.getSubTitle());
        binding.pubInfoTextView.setText(book.getPubInfo());
        binding.summaryTextView.setText(book.getSummary());

        int[] readerArray = new int[3];
        int[] starArray = new int[5];
        int starReaderSum = 0;
        Random random = new Random();
        for(int i=0; i<5; i++){
            starArray[i] = 1000*(4 - i) + random.nextInt(10000 - i*2000);
            starReaderSum = starReaderSum + starArray[i];
        }

        readerArray[0] = starReaderSum*2/10;
        readerArray[1] = starReaderSum*3/10;
        readerArray[2] = starReaderSum*5/10;
        binding.ratingMemoTextView.setText(starReaderSum + "参与评分");
        binding.ratingSummaryTextView.setText(readerArray[0] + "人读过，" + readerArray[1] + "人在读，" + readerArray[2] + "人准备读");

        float[] rates = new float[5];
        int starSum = 0;
        for(int i=0; i<5; i++){
            rates[i] = (float)starArray[i]/(float)starReaderSum;
            starSum = starSum + (5 - i)*starArray[i];
        }
        float perRate = (float)starSum/(float)(starReaderSum*5);

        binding.ratingRatingBar.setRate(perRate);
        binding.ratingDoubanRating.setRates(rates);
        binding.ratingValueTextView.setText(String.format("%.1f", perRate*10));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), bookImageResId);
        //Palette p = Palette.from(bitmap).generate();创建同步调色板
        createPaletteAsync(bitmap);
    }

}
