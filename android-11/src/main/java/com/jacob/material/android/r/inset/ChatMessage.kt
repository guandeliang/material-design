package com.jacob.material.android.r.inset

data class ChatMessage(
        val messageType:Int,
        val resId:Int
){
    companion object {
        const val SELF_STRING = 0
        const val SELF_IMAGE = 1
        const val OTHER_STRING = 2
        const val OTHER_IMAGE = 3
    }
}

