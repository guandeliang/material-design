/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */
package com.jacob.material.example.motionlayout

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.lifecycleScope
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutOneFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MotionLayoutOneFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutOneFragmentBinding

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_one_fragment, container, false)
        binding.motionLayout.setTransitionListener(MotionListener())
        return binding.getRoot()
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
            if(triggerId == R.id.motion_triger_top_right_image_view){
                binding.topRightImageView.alpha = 1f
                val aniImageView: AnimatedVectorDrawable = binding.topRightImageView.drawable as AnimatedVectorDrawable
                aniImageView.start()

            }
            if(triggerId == R.id.motion_triger_bottom_left_image_view){
                binding.bottomLeftImageView.alpha = 1f
                val aniImageView: AnimatedVectorDrawable = binding.bottomLeftImageView.drawable as AnimatedVectorDrawable
                aniImageView.start()
            }

        }
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {}
    }


}