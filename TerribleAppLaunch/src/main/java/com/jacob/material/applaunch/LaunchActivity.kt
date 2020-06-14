package com.jacob.material.applaunch

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.jacob.http.MdGlide
import com.jacob.http.MdRetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchActivity : AppCompatActivity() {


    private var tagList = mutableListOf<String>()
    private var hasShowMain:Boolean = false
    private lateinit var grayImageView:ImageView
    private lateinit var blackImageView:ImageView
    private lateinit var whiteImageView:ImageView
    private lateinit var logoImageView:ImageView
    private lateinit var motionLayout: MotionLayout




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.launch_activity)

        motionLayout = findViewById(R.id.motion_layout)
        motionLayout.setTransitionListener(MotionListener())

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


        MdGlide.with(this).load(Uri.parse(grayUrl)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(grayImageView))
        MdGlide.with(this).load(Uri.parse(blackUrl)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(blackImageView))
        MdGlide.with(this).load(Uri.parse(whiteUrl)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(whiteImageView))
        MdGlide.with(this).load(Uri.parse(logoUrl)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(LogoDrawableImageViewTarget(logoImageView))
    }



    private inner class LogoDrawableImageViewTarget(var imageView: ImageView): DrawableImageViewTarget(imageView) {

        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            super.onResourceReady(resource, transition)
            imageView.setImageDrawable(resource)

            val tag:String = imageView.tag as String

            if(!tagList.contains(tag)){
                tagList.add(tag)
            }

            if(tagList.size == 4){
            }
            motionLayout.transitionToEnd()

        }
    }


    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}

        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}

        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}

        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {

            lifecycleScope.launch {
                withContext(Dispatchers.Default){
                    delay(3000)
                }
                showMainActivity()
            }
        }
    }

    @Synchronized
    private fun  showMainActivity() {
        if(hasShowMain){
            return
        }
        hasShowMain = true
        val intent = Intent()
        intent.setClass(this@LaunchActivity, MainActivity::class.java)
        startActivity(intent)
        this.overridePendingTransition(R.anim.activity_alpha_scale_enter, android.R.anim.fade_out)
        finish()
    }
}