package com.jacob.material.terriblelaunch

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.jacob.material.terriblelaunch.databinding.LaunchActivityBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchActivity : AppCompatActivity() {

    private lateinit var binding: LaunchActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.launch_activity)
        binding.motionLayout.setTransitionListener(MotionListener())
        anyTask()
        //a bug of motionlayout in constraintlayout Version 2.0.0-beta7
        //can not start in oncreate fun
        //binding.motionLayout.transitionToEnd()
    }

    //Simulate time consuming task
    private fun anyTask(){
        SystemClock.sleep(1000)
        lifecycleScope.launch {
            withContext(Dispatchers.Default){
            }
            binding.motionLayout.transitionToEnd()
        }
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
            Log.d("MATERIAL-BOOK", "onTransitionTrigger");
        }
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
            Log.d("MATERIAL-BOOK", "onTransitionStarted");
        }
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
            Log.d("MATERIAL-BOOK", "onTransitionChange");
        }

        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {
            Log.d("MATERIAL-BOOK", "onTransitionCompleted");

            lifecycleScope.launch {
                withContext(Dispatchers.Default){
                    delay(1000)
                }
                showMainActivity()
            }
        }
    }

    private fun  showMainActivity() {
        val intent = Intent()
        intent.setClass(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}