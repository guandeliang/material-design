package com.jacob.material.applaunch

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.jacob.material.applaunch.databinding.LaunchActivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: LaunchActivityBinding
    private var hasShowMainActivity:Boolean = false

    //ActivityTaskManager: Displayed
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setTheme(R.style.AppTheme_Lunch)
        binding = DataBindingUtil.setContentView(this, R.layout.launch_activity)

        binding.motionLayout.setTransitionListener(MotionListener())

        //Simulate time consuming task
        SystemClock.sleep(1000)

        binding.motionLayout.transitionToEnd()
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}

        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}

        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}

        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {

            lifecycleScope.launch {
                withContext(Dispatchers.Default){
                    delay(500)
                }
                showMainActivity()
            }
        }
    }

    @Synchronized
    private fun  showMainActivity() {
        if(hasShowMainActivity){
            return
        }
        hasShowMainActivity = true

        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
        //this.overridePendingTransition(R.anim.activity_alpha_scale_enter, android.R.anim.fade_out)
        finish()
    }
}