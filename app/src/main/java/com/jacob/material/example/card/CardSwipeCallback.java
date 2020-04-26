package com.jacob.material.example.card;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.material.R;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

/*
    https://stackoverflow.com/questions/44965278/recyclerview-itemtouchhelper-buttons-on-swipe
    https://medium.com/@shubham_nikam/recyclerview-swipe-right-left-e420856d688e
    https://codeburst.io/android-swipe-menu-with-recyclerview-8f28a235ff28
    https://github.com/FanFataL/swipe-controller-demo/blob/master/app/src/main/java/pl/fanfatal/swipecontrollerdemo/MainActivity.java
    https://github.com/FanFataL/swipe-controller-demo/blob/master/app/src/main/java/pl/fanfatal/swipecontrollerdemo/SwipeController.java
    https://www.jianshu.com/p/c769f4ed298f
    https://blog.csdn.net/u014133119/article/details/80942932
    https://blog.csdn.net/qqqq245425070/article/details/80587271
    https://www.cnblogs.com/yc211/p/12307292.html
    https://www.sohu.com/a/138934237_675634，实现头条的办法
    https://www.ctolib.com/mip/itemtouchhelper-extension.html 不知道设么玩意
*/


public class CardSwipeCallback extends ItemTouchHelper.Callback {

    private Context context;
    private int swipeViewId;
    private int leftBackWidth;
    private int rightBackWidth;

    private boolean isSwipeBack;
    private float backDistance;

    private List<RecyclerView.ViewHolder> swipeHolderList;
    private RecyclerViewScrollListener scrollListener;

    public CardSwipeCallback(@NonNull Context context, @NonNull @IdRes int swipeViewId, int leftBackWidth, int rightBackWidth){
        this.context = context;
        this.swipeViewId = swipeViewId;
        this.leftBackWidth = leftBackWidth;
        this.rightBackWidth = rightBackWidth;

        this.isSwipeBack = false;
        this.backDistance = 0f;
        this.swipeHolderList = new ArrayList<>();
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;  //ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return Float.MAX_VALUE;
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 1.1f;
    }

    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        this.backDistance = animateDx;
        long duration = super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)*2;
        return duration;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(@NonNull Canvas c,
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY,
                            int actionState,
                            boolean isCurrentlyActive) {


        float newDx = dX;
        boolean isShowLeftBack = backDistance < 0 && leftBackWidth > 0 && Math.abs(backDistance) >  leftBackWidth;
        boolean isShowRightBack = backDistance > 0 && rightBackWidth > 0 && backDistance >  leftBackWidth;

        if(isSwipeBack && (isShowLeftBack || isShowRightBack)){
            if(isShowLeftBack && newDx < leftBackWidth){
                newDx = leftBackWidth;
            }
            if(isShowRightBack && Math.abs(newDx) < rightBackWidth){
                newDx = 0 - rightBackWidth;
            }
            if(!swipeHolderList.contains(viewHolder)){
                swipeHolderList.add(viewHolder);
            }
        }else{
            swipeHolderList.remove(viewHolder);
        }

        if(this.scrollListener == null){
            this.scrollListener = new RecyclerViewScrollListener();
            recyclerView.addOnScrollListener(this.scrollListener);
        }

        CardSwipeUIUtilImpl.INSTANCE.onDraw(c, recyclerView, getSwipeView(viewHolder), newDx, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c,
                                @NonNull RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder,
                                float dX, float dY,
                                int actionState,
                                boolean isCurrentlyActive) {
        CardSwipeUIUtilImpl.INSTANCE.onDrawOver(c, recyclerView, getSwipeView(viewHolder), dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {
        CardSwipeUIUtilImpl.INSTANCE.clearView(getSwipeView(viewHolder));
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder == null){
            isSwipeBack = true;
        }else{
            isSwipeBack = false;
            CardSwipeUIUtilImpl.INSTANCE.onSelected(getSwipeView(viewHolder));
        }
    }

    private View getSwipeView(RecyclerView.ViewHolder viewHolder){
        View cardView = viewHolder.itemView.findViewById(this.swipeViewId);
        return cardView;
    }

    private class RecyclerViewScrollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState != RecyclerView.SCROLL_STATE_IDLE){
                for(RecyclerView.ViewHolder holder : swipeHolderList){
                    View cardView = getSwipeView(holder);
                    cardView.animate().translationX(0);
                }
                swipeHolderList.clear();
            }
        }
    }

}
