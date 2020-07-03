/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */
package com.jacob.material.example.motionlayout

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleObserver
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutIntroActivityBinding

class MotionLayoutIntroActivity : AppCompatActivity() , LifecycleObserver {
    private lateinit var binding: MotionLayoutIntroActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.lifecycle.addObserver(this)
        binding = DataBindingUtil.setContentView(this, R.layout.motion_layout_intro_activity)
    }

}