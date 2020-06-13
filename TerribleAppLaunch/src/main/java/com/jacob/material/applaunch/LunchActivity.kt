package com.jacob.material.applaunch

import android.animation.Animator
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.jacob.http.MdGlide
import com.jacob.http.MdRetrofitFactory

class LunchActivity : AppCompatActivity() {


    private var tagList = mutableListOf<String>()
    private lateinit var grayImageView:ImageView
    private lateinit var blackImageView:ImageView
    private lateinit var whiteImageView:ImageView
    private lateinit var logoImageView:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.launch_activity)

        grayImageView = findViewById(R.id.gray_image_view)
        blackImageView = findViewById(R.id.black_image_view)
        whiteImageView = findViewById(R.id.white_image_view)
        logoImageView = findViewById(R.id.logo_image_view)

        grayImageView.tag = "grayImageView"
        blackImageView.tag = "blackImageView"
        whiteImageView.tag = "whiteImageView"
        logoImageView.tag = "logoImageView"

        val grayUrl = MdRetrofitFactory.BASE_URL + "images/launch_gray_bg.png"
        val blackUrl = MdRetrofitFactory.BASE_URL + "images/launch_black_bg.png"
        val whiteUrl = MdRetrofitFactory.BASE_URL + "images/launch_white_bg.png"
        val logoUrl = MdRetrofitFactory.BASE_URL + "images/launch_logo_png.png"

        logoImageView.scaleX = 0.1f
        logoImageView.scaleY = 0.1f
        logoImageView.alpha = 0f



        MdGlide.with(this).load(grayUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(grayImageView))
        MdGlide.with(this).load(blackUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(blackImageView))
        MdGlide.with(this).load(whiteUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(whiteImageView))
        MdGlide.with(this).load(logoUrl).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(logoImageView))

    }



    private inner class LogoDrawableImageViewTarget(var imageView: ImageView): DrawableImageViewTarget(imageView) {

        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            super.onResourceReady(resource, transition)
            imageView.setImageDrawable(resource)

            val tag:String = imageView.tag as String

            if(!tagList.contains(tag)){
                tagList.add(tag)
            }

            /*
            if(tagList.size == 4){

                val animator = logoImageView.animate()
                        .alpha(1f)
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(1000)
                        .setStartDelay(0)
                        .setListener(LogoAnimListener())
                animator.start()
            }

             */

        }
    }


    private inner class LogoAnimListener : Animator.AnimatorListener {
        override fun onAnimationEnd(animation: Animator) {
            showMainActivity()
        }

        override fun onAnimationStart(animation: Animator) {}

        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
        override fun onAnimationStart(animation: Animator, isReverse: Boolean) {}
        override fun onAnimationEnd(animation: Animator, isReverse: Boolean) {}
    }

    private fun showMainActivity() {
        val intent = Intent()
        intent.setClass(this@LunchActivity, MainActivity::class.java)
        //startActivity(intent)
        //finish()
    }
}