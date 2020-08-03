package com.jacob.material.android.r.notification

import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class NotificationReplyReceiver : BroadcastReceiver() {

    companion object {
        const val KEY_TEXT_REPLY = "reply"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val results = RemoteInput.getResultsFromIntent(intent) ?: return
        val input = results.getCharSequence(KEY_TEXT_REPLY)?.toString()
        val uri = intent.data ?: return
        val connectId = uri.lastPathSegment?.toLong() ?: return

        var notificationChat = NotificationChat.getInstance(context)


        if (connectId > 0 && !input.isNullOrBlank()) {
            var contacts: List<NotificationContact> = NotificationChat.CONTACTS.filter {it.id == connectId}
            if(contacts.isEmpty()){
                return
            }

            notificationChat.addMessage(connectId, input)
        }
    }
}
