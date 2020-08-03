package com.jacob.material.android.r.notification

import android.content.Context
import com.jacob.material.android.r.R
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class NotificationChat internal constructor(
        private val notificationHelper: NotificationHelper,
        private val executor: Executor
){

    companion object {
        private var instance: NotificationChat? = null

        fun getInstance(context: Context): NotificationChat {
            return instance ?: synchronized(this) {
                instance ?: NotificationChat(
                        NotificationHelper(context),
                        Executors.newFixedThreadPool(4)
                ).also {
                    instance = it
                }
            }
        }

        val CONTACTS = listOf(
                NotificationContact(1L, "James", "James Smith", R.drawable.header_001),
                NotificationContact(2L, "Michael", "Michael Smith", R.drawable.header_002),
                NotificationContact(3L, "Robert", "Robert Smith", R.drawable.header_003),
                NotificationContact(4L, "Maria", "Maria Garcia", R.drawable.header_004)
        )

    }

    private val _messages = mutableListOf(
            NotificationMessage(1L, "Hi, I am ${CONTACTS[1].descripation}", System.currentTimeMillis(), CONTACTS[1]),
            NotificationMessage(2L, "Please talk same thing", System.currentTimeMillis(), CONTACTS[1])
    )

    val messages: List<NotificationMessage>
        get() = _messages



    fun addMessage(senderId:Long, content:String):Boolean {
        var contacts: List<NotificationContact> = CONTACTS.filter {it.id == senderId}
        if(contacts.isEmpty()){
            return false
        }

        var message = NotificationMessage(
                _messages.size + 1L,
                content,
                System.currentTimeMillis(),
                contacts[0]
        )

        _messages.add(message)

        var autoMessage = NotificationMessage(
                _messages.size + 1L,
                "OK $content !!!",
                System.currentTimeMillis(),
                CONTACTS[3]
        )

        _messages.add(autoMessage)

        notificationHelper.createNotification()

        return true
    }


}