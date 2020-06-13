package com.jacob.material.applaunch;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jacob.http.MdGlide;
import com.jacob.http.MdRetrofitFactory;

public class LunchActivity extends AppCompatActivity {

    private ViewPropertyAnimator animator;

    private ImageView grayImageView;
    private ImageView blackImageView;
    private ImageView whiteImageView;
    private ImageView logoImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.launch_activity);

        grayImageView = findViewById(R.id.gray_image_view);
        blackImageView = findViewById(R.id.black_image_view);
        whiteImageView = findViewById(R.id.white_image_view);
        logoImageView = findViewById(R.id.logo_image_view);

        String grayUrl = MdRetrofitFactory.BASE_URL + "images/launch_gray_bg.png";
        String blackUrl = MdRetrofitFactory.BASE_URL + "images/launch_black_bg.png";
        String whiteUrl = MdRetrofitFactory.BASE_URL + "images/launch_white_bg.png";
        String logoUrl = MdRetrofitFactory.BASE_URL + "images/launch_logo_png.png";
        MdGlide.with(this).load(grayUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(grayImageView);
        MdGlide.with(this).load(blackUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(blackImageView);
        MdGlide.with(this).load(whiteUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(whiteImageView);
        MdGlide.with(this).load(logoUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(logoImageView);


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


        Log.d("MATERIAL_BOOK", "onCreate finish");
    }




    private class LogoAnimListener implements Animator.AnimatorListener{
        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("MATERIAL_BOOK", "onAnimationEnd");
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

    private void showMainActivity(){
        Intent intent = new Intent();
        intent.setClass(LunchActivity.this, MainActivity.class);
        //startActivity(intent);
        //finish();
    }


}