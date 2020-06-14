package com.jacob.material.applaunch

import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.jacob.material.applaunch.databinding.LaunchActivityBinding

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: LaunchActivityBinding
    private lateinit var logoAni: Animatable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.launch_activity)
        hideSystemUI()
        SystemClock.sleep(500)

        if (Build.VERSION.SDK_INT >= 24) {
            val logoDrawable = resources.getDrawable(R.drawable.launch_bg_ani, theme) as AnimatedVectorDrawable
            binding.launchBgImageView.setImageDrawable(logoDrawable)
            logoAni = logoDrawable
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoDrawable, AniVectorCallback())
        } else {
            val logoCompatDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.launch_bg_ani)
            binding.launchBgImageView.setImageDrawable(logoCompatDrawable)
            logoAni = logoCompatDrawable as Animatable
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoCompatDrawable, AniVectorCallback())
        }
        logoAni.start()
    }

    private inner class AniVectorCallback : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable) {
            binding.motionLayout.transitionToEnd()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )
    }
}