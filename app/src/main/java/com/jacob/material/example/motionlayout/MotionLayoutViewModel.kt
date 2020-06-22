/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-9 下午7:16
 *
 */
package com.jacob.material.example.motionlayout

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jacob.material.BR

class MotionLayoutViewModel : ViewModel(), Observable {
    enum class Action {
        BACK
    }

    private val callbacks = PropertyChangeRegistry()
    public var navController: NavController? = null

    override fun addOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    @Bindable
    var action: Action? = null
        set(action) {
            field = action
            callbacks.notifyCallbacks(this, BR.action, null)
        }

}