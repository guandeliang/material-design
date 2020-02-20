/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-6 下午3:32
 *
 */

package com.jacob.book.material.widgets.sliderbanner;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.jacob.book.material.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CardViewSlider extends FrameLayout{
    private ViewPager2 viewPager2;
    private Indicator indicator;
    private List<Card> cardList;
    private CardViewAdapter adapter;
    private int realItemCount;
    private int cacheItemCount;
    private Disposable disposable;

    public CardViewSlider(@NonNull Context context) {
        this(context, null);
    }

    public CardViewSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardViewSlider(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.slider_banner, this);

        viewPager2 = findViewById(R.id.view_pager_2);
        indicator = findViewById(R.id.slider_indicator);
        viewPager2.registerOnPageChangeCallback(new PageChangeCallback());

        realItemCount = 0;
        cacheItemCount = 0;
        cardList = new ArrayList<>();
        adapter = new CardViewAdapter(cardList);
        viewPager2.setAdapter(adapter);
        indicator.setVisibility(View.INVISIBLE);

    }


    public void setImageResIds(int... ids){
        cardList = new ArrayList<>();

        for(int id : ids){
            Card card = new Card();
            card.setImageResId(id);
            cardList.add(card);
        }

        realItemCount = cardList.size();

        if(realItemCount <= 1){
            adapter.setCardList(cardList);
            indicator.setVisibility(View.INVISIBLE);
            cacheItemCount = realItemCount;
            return;
        }

        if(realItemCount == 2){
            cardList.addAll(cardList);
            cardList.addAll(cardList);
        }

        if(realItemCount > 2 && realItemCount < 6){
            cardList.addAll(cardList);
        }

        cacheItemCount = cardList.size();
        cardList.addAll(cardList);
        cardList.addAll(cardList);

        indicator.setVisibility(View.VISIBLE);
        indicator.setPointCount(realItemCount);
        indicator.setCurrPointIdx(0);
        adapter.setCardList(cardList);
        viewPager2.setCurrentItem(cacheItemCount, false);

        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }

        disposable = Observable.timer(5000, TimeUnit.MILLISECONDS)
                .repeat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutoScrollConsumer());
    }

    //防止内存溢出，在主界面销毁的时候，必须执行这个方法
    public void dispose(){
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    private class PageChangeCallback extends ViewPager2.OnPageChangeCallback{
        @Override
        public void onPageSelected(int position) {
            if(realItemCount <= 0){
                return;
            }
            int currPointIdx = position % realItemCount;
            indicator.setCurrPointIdx(currPointIdx);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(realItemCount <= 1){
                return;
            }
            if(state == ViewPager2.SCROLL_STATE_IDLE){
                int position = viewPager2.getCurrentItem();
                if(position < cacheItemCount){
                    viewPager2.setCurrentItem(position + cacheItemCount, false);
                }else if(position >= cacheItemCount*2){
                    viewPager2.setCurrentItem(position - cacheItemCount, false);
                }
            }
        }
    }

    private class AutoScrollConsumer implements Consumer<Long> {
        @Override
        public void accept(Long aLong) {
            if(realItemCount <= 1){
                return;
            }

            int state = viewPager2.getScrollState();
            if(state == ViewPager2.SCROLL_STATE_IDLE){
                int position = viewPager2.getCurrentItem();
                viewPager2.setCurrentItem(position + 1, true);
            }
        }
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }


    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }

        disposable = Observable.timer(5000, TimeUnit.MILLISECONDS)
                .repeat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AutoScrollConsumer());
    }




}
