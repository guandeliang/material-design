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
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.navigation.Navigation
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutIndexFragmentBinding

class MotionLayoutIndexFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutIndexFragmentBinding

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_index_fragment, container, false)
        binding.fragment = this
        return binding.getRoot()
    }
    fun onItemClick(view: View) {
        when(view.id){
            R.id.one_title_text_view -> {
                Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_one)
            }
            R.id.two_title_text_view -> {
                Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_two)
            }
            R.id.three_title_text_view -> {
                Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_three)
            }
            R.id.four_title_text_view -> {
                Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_four)
            }
            R.id.five_title_text_view -> {
                Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_five)
            }
        }
    }


}