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
import com.jacob.material.databinding.MotionLayoutFiveFragmentBinding
import com.jacob.material.databinding.MotionLayoutFourFragmentBinding
import com.jacob.material.databinding.MotionLayoutThreeFragmentBinding
import com.jacob.material.databinding.MotionLayoutTwoFragmentBinding
import kotlin.math.roundToInt

class MotionLayoutFiveFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutFiveFragmentBinding

    private var beginTime:Long = 0

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_five_fragment, container, false)
        binding.motionLayout.setTransitionListener(MotionListener())

        binding.linearButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_linear)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.standardButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_standard)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.accelerateButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_accelerate)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.decelerateButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_decelerate)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.flipButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_flip)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.startHorizontalButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_start_horizontal)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }


        binding.startVerticalButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_start_vertical)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.deltaRelativeButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_key_position_delta)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.pathRelativeButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_key_position_path)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.parentRelativeButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_key_position_parent)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.attrOfConstraintButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_constraint_attribute)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.attrOfKeyFrameSetButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_key_frame_set_attribute)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        binding.keyCycleButton.setOnClickListener {
            binding.motionLayout.setTransition(R.id.transition_key_cycle)
            binding.motionLayout.progress = 0f
            binding.motionLayout.transitionToEnd()
        }

        return binding.getRoot()
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {}
    }

}