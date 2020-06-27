/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */
package com.jacob.material.example.motionlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutTwoFragmentBinding

class MotionLayoutTwoFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutTwoFragmentBinding

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_two_fragment, container, false)
        binding.motionLayout.setTransitionListener(MotionListener())
        return binding.getRoot()
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {}
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {}
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {}
        override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
            if(currentId < 0){
                var ty = context!!.resources.getDimensionPixelSize(R.dimen.dp_4) * 75.toFloat()

                var nextTransition:Int = -1;
                when(motionLayout!!.currentState){
                    R.id.ball_zone ->{
                        nextTransition = R.id.transition_zone_to_one
                        binding.picOneImageView.animate().scaleX(1f).scaleY(1f).setDuration(300)
                        binding.oneTitleTextView.animate().translationX(0f).setDuration(300)
                    }
                    R.id.ball_one ->{
                        nextTransition = R.id.transition_one_to_two
                        binding.picTwoImageView.animate().scaleX(1f).scaleY(1f).setDuration(300)
                        binding.oneTitleTextView.animate().translationX(ty).setDuration(300)
                        binding.twoTitleTextView.animate().translationX(0f).setDuration(300)
                    }
                    R.id.ball_two ->{
                        nextTransition = R.id.transition_two_to_three
                        binding.picThreeImageView.animate().scaleX(1f).scaleY(1f).setDuration(300)
                        binding.twoTitleTextView.animate().translationX(ty).setDuration(300)
                        binding.threeTitleTextView.animate().translationX(0f).setDuration(300)
                    }
                    R.id.ball_three ->{
                        nextTransition = R.id.transition_three_to_four
                        binding.picFourImageView.animate().scaleX(1f).scaleY(1f).setDuration(300)
                        binding.threeTitleTextView.animate().translationX(ty).setDuration(300)
                        binding.fourTitleTextView.animate().translationX(0f).setDuration(300)
                    }
                    R.id.ball_four ->{
                        nextTransition = R.id.transition_four_to_five
                        binding.picFiveImageView.animate().scaleX(1f).scaleY(1f).setDuration(300)
                        binding.fourTitleTextView.animate().translationX(ty).setDuration(300)
                        binding.fiveTitleTextView.animate().translationX(0f).setDuration(300)
                    }
                }
                if(nextTransition > 0){
                    motionLayout!!.setTransition(nextTransition)
                    motionLayout!!.transitionToEnd()
                }
            }
        }
    }


}