package com.jacob.material.elegantlaunch

import android.content.Intent
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.jacob.material.elegantlaunch.databinding.LaunchActivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: LaunchActivityBinding
    private var hasShowMainActivity:Boolean = false
    private var hasFinishTask:Boolean = false
    private var hasFinishAni:Boolean = false

    private lateinit var logoAni: AnimatedVectorDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.launch_activity)

        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )

        binding.motionLayout.setTransitionListener(MotionListener())

        logoAni = binding.launchBgImageView.drawable as AnimatedVectorDrawable
        AnimatedVectorDrawableCompat.registerAnimationCallback(logoAni, AniVectorCallback())

        anyTask()

        logoAni.start()
    }

    //Simulate time consuming task
    private fun anyTask(){
        lifecycleScope.launch {
            withContext(Dispatchers.Default){
                delay(1000)
            }
            hasFinishTask = true
            showMainActivity()
        }
    }


    private inner class AniVectorCallback : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable) {
            binding.motionLayout.transitionToEnd()
        }
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}

        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {
            lifecycleScope.launch {
                withContext(Dispatchers.Default){
                    delay(1000)
                }
                hasFinishAni = true
                showMainActivity()
            }
        }
    }

    @Synchronized
    private fun  showMainActivity() {
        if(!hasFinishTask or !hasFinishAni){
            return
        }

        if(hasShowMainActivity){
            return
        }
        hasShowMainActivity = true

        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
        this.overridePendingTransition(R.anim.activity_alpha_scale_enter, android.R.anim.fade_out)
        finish()
    }

}