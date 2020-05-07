/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.card;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.card.MaterialCardView;
import com.jacob.material.R;
import com.jacob.material.databinding.CardDragHelperActivityBinding;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.example.model.Thrones;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class CardDragHelperActivity extends AppCompatActivity {
    private CardDragHelperActivityBinding binding;
    private Rect cardRect;
    private Rect layoutRect;
    private List<AddressBook> addressBookList;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_drag_helper_activity);
        WidgetsUtils.setSystemBarColor(this, android.R.attr.colorBackground);
        WidgetsUtils.setSystemBarLight(this);
        WidgetsUtils.setNavigationBarColor(this, android.R.attr.colorBackground);

        ViewTreeObserver viewTreeObserver = binding.constraintLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //Expand Favorite Text View Touche Delegate;
                    binding.constraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Rect delegateArea = new Rect();
                    binding.favoriteTextView.getHitRect(delegateArea);
                    delegateArea.left += 100;
                    delegateArea.top += 100;
                    delegateArea.right += 100;
                    delegateArea.bottom += 100;
                    TouchDelegate touchDelegate = new TouchDelegate(delegateArea, binding.favoriteTextView);
                    ((View)binding.favoriteTextView.getParent()).setTouchDelegate(touchDelegate);
                    //save card ori position
                    if(cardRect == null){
                        cardRect = new Rect();
                        cardRect.left = binding.pictureTwoCardView.getLeft();
                        cardRect.top = binding.pictureTwoCardView.getTop();
                        cardRect.right = binding.pictureTwoCardView.getRight();
                        cardRect.bottom = binding.pictureTwoCardView.getBottom();
                    }
                    if(layoutRect == null){
                        layoutRect = new Rect();
                        layoutRect.left = binding.constraintLayout.getLeft();
                        layoutRect.top = binding.constraintLayout.getTop();
                        layoutRect.right = binding.constraintLayout.getRight();
                        layoutRect.bottom = binding.constraintLayout.getBottom();
                    }
                }
            });
        }
        binding.pictureOneCardView.setVisibility(View.INVISIBLE);
        binding.pictureOneCardView.setAlpha(0f);

        binding.constraintLayout.addDraggableCardView(binding.pictureOneCardView);
        binding.constraintLayout.addDraggableCardView(binding.pictureTwoCardView);

        binding.constraintLayout.setViewDragListener(new CardDragListener());


        addressBookList = JsonUtils.loadAddressBooks(getResources());
        position = 0;
        setData(binding.pictureTwoCardView);
        setData(binding.pictureOneCardView);

        binding.deleteFab.setOnClickListener(new DeleteClickListener());
        binding.favoriteFab.setOnClickListener(new FavoriteClickListener());

    }

    private void setData(MaterialCardView  cardView){
        ImageView pictureImageView = null;
        TextView nameTextView = null;
        TextView mailTextView = null;
        if(cardView.getId() == R.id.picture_one_card_view){
            pictureImageView = binding.pictureOneImageView;
            nameTextView = binding.nameOneTextView;
            mailTextView = binding.mailOneTextView;
        }else{
            pictureImageView = binding.pictureTwoImageView;
            nameTextView = binding.nameTwoTextView;
            mailTextView = binding.mailTwoTextView;
        }

        if(position <0 || position >= addressBookList.size()){
            position = 0;
        }

        AddressBook addressBook = addressBookList.get(position);
        int imageResId = getResources().getIdentifier(addressBook.getFileName(), "drawable", getPackageName());
        pictureImageView.setImageResource(imageResId);
        nameTextView.setText(addressBook.getTitle());
        mailTextView.setText(addressBook.getMail());
        position = position + 1;
    }


    private class DeleteClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            MaterialCardView cardView = null;
            MaterialCardView otherCardView = null;

            if(binding.pictureOneCardView.getVisibility() == View.VISIBLE){
                cardView = binding.pictureOneCardView;
                otherCardView = binding.pictureTwoCardView;
            }else{
                cardView = binding.pictureTwoCardView;
                otherCardView = binding.pictureOneCardView;
            }
            otherCardView.setAlpha(1);
            otherCardView.setVisibility(View.VISIBLE);

            cardView.setDragged(true);
            swipeCardView(cardView, 0-layoutRect.right, 0-layoutRect.bottom/2, 500);
        }
    }

    private class FavoriteClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            MaterialCardView cardView = null;
            MaterialCardView otherCardView = null;

            if(binding.pictureOneCardView.getVisibility() == View.VISIBLE){
                cardView = binding.pictureOneCardView;
                otherCardView = binding.pictureTwoCardView;
            }else{
                cardView = binding.pictureTwoCardView;
                otherCardView = binding.pictureOneCardView;
            }
            otherCardView.setAlpha(1);
            otherCardView.setVisibility(View.VISIBLE);

            cardView.setDragged(true);
            swipeCardView(cardView, layoutRect.right, 0-layoutRect.bottom/2, 500);
        }
    }


    private void swipeCardView(MaterialCardView cardView, float tranX, float tranY, int dura){
        ViewPropertyAnimator animator = cardView.animate().translationX(tranX).translationY(tranY).setDuration(dura);
        new CountDownTimer(dura, 50) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (animator != null){
                    animator.cancel();
                }
                resetCardView(cardView, false);
            }

        }.start();

    }



    private void resetCardView(MaterialCardView  cardView, boolean isFront){
        MaterialCardView otherCardView = null;
        if(cardView.getId() == R.id.picture_one_card_view){
            otherCardView = binding.pictureTwoCardView;
        }else{
            otherCardView = binding.pictureOneCardView;
        }

        cardView.setDragged(false);

        if(isFront){
            cardView.setAlpha(1);
            cardView.setVisibility(View.VISIBLE);
            cardView.setElevation(WidgetsUtils.dpToPx(this, 1));
            binding.constraintLayout.bringChildToFront(cardView);

            otherCardView.setAlpha(0);
            otherCardView.setVisibility(View.INVISIBLE);
            otherCardView.setElevation(WidgetsUtils.dpToPx(this, 0));
        }else{
            cardView.setAlpha(0);
            cardView.setVisibility(View.INVISIBLE);
            cardView.setElevation(WidgetsUtils.dpToPx(this, 0));
            setData(cardView);


            otherCardView.setAlpha(1);
            otherCardView.setVisibility(View.VISIBLE);
            otherCardView.setElevation(WidgetsUtils.dpToPx(this, 1));
            binding.constraintLayout.bringChildToFront(otherCardView);
        }

        cardView.setTranslationX(0);
        cardView.setTranslationY(0);
        cardView.setLeft(cardRect.left);
        cardView.setTop(cardRect.top);
        cardView.setRight(cardRect.right);
        cardView.setBottom(cardRect.bottom);

    }

    private class CardDragListener implements DraggableCardConstraintLayout.ViewDragListener{

        @Override
        public void onViewCaptured(@NonNull MaterialCardView cardView, int left, int top) {
            cardView.setDragged(true);
        }

        @Override
        public void onViewMove(@NonNull MaterialCardView cardView, int newLeft, int newTop) {
            float swipeDis = (cardRect.right - cardRect.left)/3;
            float distance = (float)Math.pow(Math.pow(newLeft - cardRect.left, 2) + Math.pow(newTop - cardRect.top, 2), 0.5);
            float alpha = distance/swipeDis;
            if(alpha > 1f){
                alpha = 1f;
            }

            MaterialCardView otherCardView = null;
            if(cardView.getId() == R.id.picture_one_card_view){
                otherCardView = binding.pictureTwoCardView;
            }else{
                otherCardView = binding.pictureOneCardView;
            }
            if(swipeDis > 0){
                otherCardView.setAlpha(alpha);
                if(otherCardView.getVisibility() != View.VISIBLE){
                    otherCardView.setVisibility(View.VISIBLE);
                }
            }
        }


        @Override
        public void onViewReleased(@NonNull MaterialCardView cardView, int left, int top, float xVel, float yVel) {
            float minVel = WidgetsUtils.dpToPx(CardDragHelperActivity.this, 120);
            float maxVel = WidgetsUtils.dpToPx(CardDragHelperActivity.this, 800);

            float minDis = (cardRect.right - cardRect.left)/3;
            double distance = Math.pow(Math.pow(left - cardRect.left, 2) + Math.pow(top - cardRect.top, 2), 0.5);

            if(xVel == 0 || (Math.abs(xVel) <= minVel && Math.abs(yVel) <= minVel) || distance < minDis){
                resetCardView(cardView, true);
            }else{
                xVel = Math.min(xVel, maxVel);
                yVel = Math.min(yVel, maxVel);

                float tranX;
                float tranY;
                if(xVel > 0){
                    tranX = layoutRect.right - left;
                }else{
                    tranX = layoutRect.left - cardView.getRight();
                }

                if(yVel > 0){
                    tranY = layoutRect.bottom - top;
                }else if(yVel < 0){
                    tranY = layoutRect.top - cardView.getBottom();
                }else{
                    tranY = 0;
                }

                float duraX = Math.abs(tranX/xVel);
                float duraY = 0f;
                if(tranY != 0){
                    duraY = Math.abs(tranY/yVel);
                }

                float secondDura = Math.min(duraX, duraY);
                int mSecondDura = (int) Math.ceil(secondDura * 1050);
                swipeCardView(cardView, xVel*secondDura, yVel*secondDura, mSecondDura);
            }
        }
    }



}
