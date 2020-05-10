/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.card;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.CardSwipeDismissActivityBinding;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class CardSwipeDismissActivity extends AppCompatActivity {
    private CardSwipeDismissActivityBinding binding;
    private SwipeDismissBehavior<View> swipeDismissBehavior;
    private Chip[] directionChips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_swipe_dismiss_activity);
        binding.setActivity(this);

        directionChips = new Chip[3];
        directionChips[0] = binding.leftChoiceChip;
        directionChips[1] = binding.anyChoiceChip;
        directionChips[2] = binding.rightChoiceChip;



        swipeDismissBehavior = new SwipeDismissBehavior<>();
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);

        CoordinatorLayout.LayoutParams coordinatorParams = (CoordinatorLayout.LayoutParams)binding.cardView.getLayoutParams();

        coordinatorParams.setBehavior(swipeDismissBehavior);

        swipeDismissBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View view) {
                Snackbar.make(binding.constraintLayout, "Card Dismissed", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Undo", v -> resetCard(binding.cardView)).show();
            }

            @Override
            public void onDragStateChanged(int state) {
                CardSwipeDismissActivity.this.onDragStateChanged(state, binding.cardView);
            }
        });

    }


    public void onSwipeDirection(CompoundButton compoundButton, boolean b){
        if(!b){
            boolean hasAnyChecked = false;
            for(Chip c:directionChips){
                if (c.isChecked()){
                    hasAnyChecked = true;
                }
            }
            if(!hasAnyChecked){
                compoundButton.setChecked(true);
            }
        }else{
            for(Chip c:directionChips){
                if(c.getId() != compoundButton.getId()){
                    c.setChecked(false);
                }
            }
            if(compoundButton.getId() == R.id.left_choice_chip){
                swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START);
            }else if(compoundButton.getId() == R.id.right_choice_chip){
                swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
            }else{
                swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
            }

        }






    }


    private void onDragStateChanged(int state, MaterialCardView cardView) {
        switch (state) {
            case SwipeDismissBehavior.STATE_DRAGGING:
            case SwipeDismissBehavior.STATE_SETTLING:
                cardView.setDragged(true);
                break;
            case SwipeDismissBehavior.STATE_IDLE:
                cardView.setDragged(false);
                break;
            default: // fall out
        }
    }

    private void resetCard(MaterialCardView cardView) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) cardView.getLayoutParams();
        int dx_8 = WidgetsUtils.dpToPx(this, 8);
        params.setMargins(dx_8*3, dx_8*3, dx_8*3, dx_8*3);
        cardView.setAlpha(1.0f);
        cardView.requestLayout();
    }


}
