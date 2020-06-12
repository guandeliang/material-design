package com.jacob.material.applaunch;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class LunchActivity extends AppCompatActivity {

    private ViewPropertyAnimator animator;
    private boolean animatorEnd;
    private boolean taskEnd;
    private boolean hasShowMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.launch_activity);

        animatorEnd = false;
        taskEnd = false;
        hasShowMain = false;

        ImageView logoImageView = findViewById(R.id.logo_image_view);
        logoImageView.setScaleX(0.1f);
        logoImageView.setScaleY(0.1f);
        logoImageView.setAlpha(0f);

        ViewPropertyAnimator animator =  logoImageView.animate()
                .alpha(1)
                .scaleX(1)
                .scaleY(1)
                .setDuration(1000)
                .setStartDelay(0)
                .setListener(new LogoAnimListener());
        animator.start();

        taskSimulationA();

        Log.d("MATERIAL_BOOK", "onCreate finish");
    }

    @SuppressLint("CheckResult")
    private void taskSimulationA(){
        Flowable.fromCallable(()-> {
            for(int i=0; i<10; i++){
                Thread.sleep(50);
            }
            return true;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe((result)-> taskSimulationB());

    }

    @SuppressLint("CheckResult")
    private void taskSimulationB(){
        Flowable.fromCallable(()-> {
            for(int i=0; i<10; i++){
                Thread.sleep(50);
            }
            return true;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe((success)-> taskSimulationC());
    }

    @SuppressLint("CheckResult")
    private void taskSimulationC(){
        Flowable.fromCallable(()-> {
            for(int i=0; i<10; i++){
                Thread.sleep(50);
            }
            return true;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.single())
        .subscribe((success)-> {
            taskEnd = true;
            showMainActivity();
        });
    }



    private class LogoAnimListener implements Animator.AnimatorListener{
        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("MATERIAL_BOOK", "onAnimationEnd");
            animatorEnd = true;
            showMainActivity();
        }

        @Override
        public void onAnimationStart(Animator animation) {
            Log.d("MATERIAL_BOOK", "onAnimationStart");
        }

        @Override
        public void onAnimationCancel(Animator animation) {}

        @Override
        public void onAnimationRepeat(Animator animation) {}

        @Override
        public void onAnimationStart(Animator animation, boolean isReverse) {}
        @Override
        public void onAnimationEnd(Animator animation, boolean isReverse) {
        }
    }

    synchronized private void showMainActivity(){
        if(!animatorEnd || !taskEnd || hasShowMain){
            return;
        }

        hasShowMain = true;

        Intent intent = new Intent();
        intent.setClass(LunchActivity.this, MainActivity.class);
        //startActivity(intent);
        //finish();
    }


}