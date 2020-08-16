package com.jacob.material.android.r.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.SystemClock
import android.util.Log
import androidx.core.net.toUri
import com.jacob.material.android.r.R

class NotificationHelper(private val context: Context) {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "notification_channel_id"
        const val NOTIFICATION_CHANNEL_NAME = "notification_channel_name"
        const val notificationId:Int = 99
    }

    private val shortcutManager = context.getSystemService<ShortcutManager>(ShortcutManager::class.java)
    private val notificationManager = context.getSystemService<NotificationManager> (NotificationManager::class.java)

    init {
        setUpNotificationChannels()
    }

    fun createShortcut(){
        var shortcuts = NotificationChat.CONTACTS.map { contact ->
            val icon = Icon.createWithResource(context, contact.icon)

            var person = Person.Builder()
                    .setName(contact.name)
                    .setIcon(icon)
                    .build()

            var intent = Intent(context, NotificationActivity::class.java)
                    .setAction(Intent.ACTION_VIEW)
                    .setData(Uri.parse(contact.shortcutActionUri))

            ShortcutInfo.Builder(context, contact.shortcutId)
                    .setLongLived(true)
                    .setShortLabel(contact.name)
                    .setLongLabel(contact.description)
                    .setIcon(icon)
                    .setPerson(person)
                    .setCategories(setOf("com.jacob.material.bubbles.category.TEXT_SHARE_TARGET"))
                    .setIntent(intent)
                    .build()
        }
        shortcutManager.addDynamicShortcuts(shortcuts)
    }

    fun removeShortcut(){
        var shortcutIds = NotificationChat.CONTACTS.map { contact ->
            contact.shortcutId
        }
        shortcutManager!!.removeLongLivedShortcuts(shortcutIds)
    }

    private fun setUpNotificationChannels() {
        if (notificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID) == null) {
            val notificationChannel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    // The importance must be IMPORTANCE_HIGH to show Bubbles.
                    NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun createNotification(){
        var notificationChat = NotificationChat.getInstance(context)
        var receiverContact =NotificationChat.CONTACTS[0]
        val receiverHeader = Icon.createWithResource(context, receiverContact.icon)
        val receiver = Person.Builder()
                .setName(receiverContact.name)
                .setIcon(receiverHeader).build()

        var style = Notification.MessagingStyle(receiver)
                .setGroupConversation(true)
                .apply {
                    for (m in notificationChat.messages) {
                        val senderHeader = Icon.createWithResource(context, m.sender.icon)
                        val sender = Person.Builder()
                                .setName(m.sender.name)
                                .setIcon(senderHeader).build()

                        val message = Notification.MessagingStyle.Message(
                                m.content,
                                m.timestamp,
                                sender
                        )
                        addMessage(message)
                    }
                }

        var action = Notification.Action
                .Builder(
                        Icon.createWithResource(context, R.drawable.icon_baseline_send),
                        "Reply",
                        PendingIntent.getBroadcast(
                                context,
                                2,
                                Intent(context, NotificationReplyReceiver::class.java)
                                        .setData(receiverContact.notificationReplyUri.toUri()),
                                PendingIntent.FLAG_UPDATE_CURRENT
                        )
                )
                .addRemoteInput(
                        RemoteInput.Builder(NotificationReplyReceiver.KEY_TEXT_REPLY)
                                .setLabel("Input some thing")
                                .build()
                )
                .setAllowGeneratedReplies(false)
                .build()

        val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, NotificationActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(Uri.parse(receiverContact.notificationPendingUri)),
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = Notification.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_baseline_message)
                .setContentIntent(pendingIntent)
                .setStyle(style)
                .addAction(action)
                .setAutoCancel(true)
                .build()

        notificationManager.notify(notificationId, notification)
    }


    fun addShortcutToNotification(){
        var notificationChat = NotificationChat.getInstance(context)
        var receiverContact =NotificationChat.CONTACTS[0]
        val receiverHeader = Icon.createWithResource(context, receiverContact.icon)
        val receiver = Person.Builder()
                .setName(receiverContact.name)
                .setIcon(receiverHeader).build()

        var style = Notification.MessagingStyle(receiver)
                .setGroupConversation(true)
                .apply {
                    for (m in notificationChat.messages) {
                        val senderHeader = Icon.createWithResource(context, m.sender.icon)
                        val sender = Person.Builder()
                                .setName(m.sender.name)
                                .setIcon(senderHeader).build()

                        val message = Notification.MessagingStyle.Message(
                                m.content,
                                m.timestamp,
                                sender
                        )
                        addMessage(message)
                    }
                }

        var action = Notification.Action
                .Builder(
                        Icon.createWithResource(context, R.drawable.icon_baseline_send),
                        "Reply",
                        PendingIntent.getBroadcast(
                                context,
                                2,
                                Intent(context, NotificationReplyReceiver::class.java)
                                        .setData(receiverContact.notificationReplyUri.toUri()),
                                PendingIntent.FLAG_UPDATE_CURRENT
                        )
                )
                .addRemoteInput(
                        RemoteInput.Builder(NotificationReplyReceiver.KEY_TEXT_REPLY)
                                .setLabel("Input some thing")
                                .build()
                )
                .setAllowGeneratedReplies(false)
                .build()

        val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, NotificationActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(Uri.parse(receiverContact.notificationPendingUri)),
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        createShortcut()
        var senderContact =NotificationChat.CONTACTS[1]
        val senderHeader = Icon.createWithResource(context, senderContact.icon)

        val bubbleMetadata = Notification.BubbleMetadata.Builder(pendingIntent, senderHeader)
                .setDesiredHeight(450)
                .setAutoExpandBubble(true)
                .setSuppressNotification(true)
                .build()

        val notification = Notification.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_baseline_message)
                .setContentIntent(pendingIntent)
                .setStyle(style)
                .addAction(action)
                .setAutoCancel(true)
                .setShortcutId(senderContact.shortcutId)
                .setBubbleMetadata(bubbleMetadata)
                .build()

        notificationManager.notify(notificationId, notification)
    }



}