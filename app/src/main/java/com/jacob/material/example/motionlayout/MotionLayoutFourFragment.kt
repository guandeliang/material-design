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
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutFourFragmentBinding
import com.jacob.material.databinding.MotionLayoutThreeFragmentBinding
import com.jacob.material.databinding.MotionLayoutTwoFragmentBinding

class MotionLayoutFourFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutFourFragmentBinding

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_four_fragment, container, false)
        binding.motionLayout.setTransitionListener(MotionListener())
        return binding.getRoot()
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
            if(triggerId == R.id.trigger_001){
                binding.titleTextView.text = "Android Programer"
            }
            if(triggerId == R.id.trigger_002){
                binding.titleTextView.text = "Material Design"
            }

            if(triggerId == R.id.trigger_003){
                binding.titleTextView.text = "Motion Layout"
            }

            if(triggerId == R.id.trigger_004){
                binding.titleTextView.text = "Custom Attribute"
            }

            if(triggerId == R.id.trigger_005){
                binding.titleTextView.text = "Constraint Layout"
            }
        }
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
            Log.d("MATERIAL_BOOK", "binding.motionLayout.transitionTimeMs " + binding.motionLayout.transitionTimeMs + ", progress = " + progress)

        }
        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {}
    }

}