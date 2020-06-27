/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */
package com.jacob.material.example.motionlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.bumptech.glide.manager.LifecycleListener
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutActivityBinding

class MotionLayoutActivity : AppCompatActivity() , LifecycleObserver {
    private lateinit var binding: MotionLayoutActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.lifecycle.addObserver(this)
        binding = DataBindingUtil.setContentView(this, R.layout.motion_layout_activity)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResumeEvent() {
        this.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }


}