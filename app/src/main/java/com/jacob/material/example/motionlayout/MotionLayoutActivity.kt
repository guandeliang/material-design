/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */
package com.jacob.material.example.motionlayout

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jacob.material.BR
import com.jacob.material.R
import com.jacob.material.databinding.MotionLayoutActivityBinding

class MotionLayoutActivity : AppCompatActivity() {
    private lateinit var binding: MotionLayoutActivityBinding
    private lateinit var viewModel: MotionLayoutViewModel
    private var lastPressBackTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.motion_layout_activity)

        this.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        viewModel = ViewModelProvider(this).get(MotionLayoutViewModel::class.java)
        viewModel.addOnPropertyChangedCallback(ViewModelPropertyChangedCallback())

        lastPressBackTime = 0

        this.onBackPressedDispatcher.addCallback(BackPressedCallback())

        val fragmentContainerId = binding.fragmentContainerView.id
        val navHostFragment = NavHostFragment.create(R.navigation.motion_layout_graph)
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction().add(fragmentContainerId, navHostFragment, "nav_host_fragment")
        fragmentTransaction.commitNow()

        viewModel.navController = navHostFragment.navController
    }

    private inner class ViewModelPropertyChangedCallback : OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            if (propertyId == BR.action) {
                val action = viewModel.action
                if (action === MotionLayoutViewModel.Action.BACK) {
                    if (viewModel.navController!!.currentDestination!!.id == R.id.index) {
                        val now = System.currentTimeMillis()
                        if (now - lastPressBackTime < 2000) {
                            finish()
                        } else {
                            lastPressBackTime = now
                            val toast = Toast.makeText(this@MotionLayoutActivity, "再按一次退出", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER, 0, 0)
                            toast.show()
                        }
                    } else {
                        viewModel.navController!!.popBackStack()
                    }
                }
            }
        }
    }

    private inner class BackPressedCallback : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.action = MotionLayoutViewModel.Action.BACK
        }
    }
}