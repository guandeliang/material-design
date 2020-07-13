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
import androidx.navigation.Navigation
import com.jacob.material.R
import com.jacob.material.databinding.*
import kotlin.math.roundToInt

class MotionLayoutDetailFragment : Fragment(), LifecycleObserver {
    private lateinit var binding: MotionLayoutDetailFragmentBinding

    private var beginTime:Long = 0

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_detail_fragment, container, false)

        binding.keyPositionTypeDeltaRelativeButton.setOnClickListener {
            val bundle:Bundle = Bundle()
            bundle.putInt(MotionLayoutKeyPositionTypeFragment.KEY_POSITION_TYPE, MotionLayoutKeyPositionTypeFragment.KEY_POSITION_TYPE_DELTA)
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_key_position_type, bundle)
        }

        binding.keyPositionTypeParentRelativeButton.setOnClickListener {
            val bundle:Bundle = Bundle()
            bundle.putInt(MotionLayoutKeyPositionTypeFragment.KEY_POSITION_TYPE, MotionLayoutKeyPositionTypeFragment.KEY_POSITION_TYPE_PARENT)
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_key_position_type, bundle)
        }

        binding.keyPositionTypePathRelativeButton.setOnClickListener {
            val bundle:Bundle = Bundle()
            bundle.putInt(MotionLayoutKeyPositionTypeFragment.KEY_POSITION_TYPE, MotionLayoutKeyPositionTypeFragment.KEY_POSITION_TYPE_PATH)
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_key_position_type, bundle)
        }

        binding.transitionEasingButton.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_transition_easing)
        }

        binding.attributePiecesButton.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_attribute_pieces)
        }

        binding.attributeRotationButton.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view).navigate(R.id.show_attribute_rotation)
        }

        return binding.getRoot()
    }


}