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
import androidx.lifecycle.LifecycleObserver
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutKeyPositionTypeFragmentBinding

class MotionLayoutKeyPositionTypeFragment : Fragment(), LifecycleObserver {
    companion object {
        var KEY_POSITION_TYPE:String = "KEY_POSITION_TYPE"
        var KEY_POSITION_TYPE_DELTA: Int = 0
        var KEY_POSITION_TYPE_PARENT: Int = 1
        var KEY_POSITION_TYPE_PATH: Int = 2
    }
    private lateinit var binding: MotionLayoutKeyPositionTypeFragmentBinding

    init {
        this.lifecycle.addObserver(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.motion_layout_key_position_type_fragment, container, false)
        var keyPositionType:Int = requireArguments().getInt(KEY_POSITION_TYPE)

        when(keyPositionType){
            KEY_POSITION_TYPE_DELTA ->
                binding.motionLayout.loadLayoutDescription(R.xml.motion_layout_key_position_type_delta_fragment_scene)
            KEY_POSITION_TYPE_PARENT ->
                binding.motionLayout.loadLayoutDescription(R.xml.motion_layout_key_position_type_parent_fragment_scene)
            KEY_POSITION_TYPE_PATH ->
                binding.motionLayout.loadLayoutDescription(R.xml.motion_layout_key_position_type_path_fragment_scene)
        }

        return binding.getRoot()
    }


}