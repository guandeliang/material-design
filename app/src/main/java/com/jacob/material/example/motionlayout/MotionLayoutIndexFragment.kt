/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */
package com.jacob.material.example.motionlayout

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.jacob.material.BR
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutIndexFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MotionLayoutIndexFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutIndexFragmentBinding
    private lateinit var viewModel: MotionLayoutViewModel

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_index_fragment, container, false)
        //binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_mulit_back_drop_fragment, container, false);
        viewModel = ViewModelProvider(requireActivity()).get<MotionLayoutViewModel>(MotionLayoutViewModel::class.java)

        binding.fragment = this

        binding.motionLayout.setTransitionListener(MotionListener())

        return binding.getRoot()
    }

    fun onItemClick(view: View) {
        when(view.id){
            R.id.one_title_text_view -> {
                viewModel.navController!!.navigate(R.id.show_one)
            }
        }
    }

    private inner class MotionListener : MotionLayout.TransitionListener {
        override fun onTransitionTrigger(motionLayout: MotionLayout?, triggerId: Int, positive: Boolean, progress: Float) {
            Log.d("MATERIAL_BOOK", "onTransitionTrigger " + progress)
        }
        override fun onTransitionStarted(motionLayout: MotionLayout?, startId: Int, endId: Int) {
            Log.d("MATERIAL_BOOK", "onTransitionStarted")
        }
        override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
            Log.d("MATERIAL_BOOK", "onTransitionChange " + progress)
        }

        override fun onTransitionCompleted(motionLayout: MotionLayout?, state: Int) {
            Log.d("MATERIAL_BOOK", "onTransitionCompleted ")
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResumeEvent() {
        //binding.motionLayout.progress = 0f
        //binding.motionLayout.transitionToEnd()

        binding.motionLayout.progress = 0.001f
        binding.motionLayout.transitionToEnd()
        /*
        lifecycleScope.launch {
            withContext(Dispatchers.Default){
            }
            binding.motionLayout.progress = 0.001f
            binding.motionLayout.transitionToEnd()
        }

         */

        Log.d("MATERIAL_BOOK", "ON_RESUME " + binding.motionLayout.progress)
    }


}