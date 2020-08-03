package com.jacob.material.android.r.notification

import android.app.*
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.Color
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.jacob.material.android.r.R
import com.jacob.material.android.r.databinding.NotificationActivityBinding

class NotificationActivity : AppCompatActivity() {

    private var _binding: NotificationActivityBinding? = null
    private val binding: NotificationActivityBinding get() = _binding!!

    private val shortcutId:String ="shortcat_id_01"
    private val notificationId:Int = 99

    private lateinit var notificationHelper: NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = NotificationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationHelper = NotificationHelper(this)

        binding.createShortcutButton.setOnClickListener {
            notificationHelper.createShortcut()
        }

        binding.removeShortcutButton.setOnClickListener {
            notificationHelper.removeShortcut()
        }

        binding.createNotificationButton.setOnClickListener {
            SystemClock.sleep(5000)
            notificationHelper.createNotification()
        }


        binding.addShortcutToNotificationButton.setOnClickListener {
            SystemClock.sleep(5000)
            notificationHelper.addShortcutToNotification()
        }

        binding.shortcutInfoTextView.text = "No Shortcut Selected"
        binding.shortcutInfoTextView.setTextColor(Color.BLACK)
        binding.pendingInfoTextView.text = "No Pending Receive"
        binding.pendingInfoTextView.setTextColor(Color.BLACK)

        if (savedInstanceState == null) {
            intent?.let(::handleIntent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    private fun handleIntent(intent: Intent) {
        Log.d("MATERIAL_BOOK", "222 ${intent.data}")
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val pathSegments = intent.data?.pathSegments
                if(pathSegments == null || pathSegments.isEmpty() || pathSegments.size < 2){
                    return
                }
                val id = pathSegments[pathSegments.size - 1].toLongOrNull()
                val category = pathSegments[pathSegments.size - 2]
                if (id != null) {
                    var contacts: List<NotificationContact> = NotificationChat.CONTACTS.filter {it.id == id}
                    if(contacts.isEmpty()){
                        return
                    }

                    if(NotificationContact.URI_CATEGORY_SHORTCUT.equals(category)){
                        binding.shortcutInfoTextView.text = "Shortcut of ${contacts[0].descripation}"
                        binding.shortcutInfoTextView.setTextColor(Color.RED)
                    }else if(NotificationContact.URI_CATEGORY_PENDING.equals(category)){
                        binding.pendingInfoTextView.text = "Pending of ${contacts[0].descripation}"
                        binding.pendingInfoTextView.setTextColor(Color.RED)
                    }
                }
            }
        }
    }



    private fun createShortcut_back(){
        val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)

        val icon = Icon.createWithResource(this@NotificationActivity, R.drawable.header_006)

        var person = Person.Builder()
                .setName("Cat")
                .setIcon(icon)
                .build()

        var intent = Intent(this@NotificationActivity, NotificationActivity::class.java)
                .setAction(Intent.ACTION_VIEW)
                .setData(
                        Uri.parse(
                                "https://com.jacob.material/shortcut/123"
                        )
                )

        val shortcut = ShortcutInfo.Builder(this@NotificationActivity, shortcutId)
                .setLongLived(true)
                .setShortLabel("Shortcut")
                .setLongLabel("Removeable Shortcut")
                .setIcon(icon)
                .setPerson(person)
                .setCategories(setOf("com.jacob.material.bubbles.category.TEXT_SHARE_TARGET"))
                .setIntent(intent)
                .build()
        shortcutManager!!.addDynamicShortcuts(listOf(shortcut))
    }

    private fun createShortcut_1(){
        val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)

        val icon = Icon.createWithResource(this@NotificationActivity, R.drawable.header_006)

        var person = Person.Builder()
                .setName("Cat")
                .setIcon(icon)
                .build()

        var intent = Intent(this@NotificationActivity, NotificationActivity::class.java)
                .setAction(Intent.ACTION_VIEW)
                .setData(
                        Uri.parse(
                                "https://com.jacob.material/shortcut/123"
                        )
                )

        val shortcut = ShortcutInfo.Builder(this@NotificationActivity, "contact_1")
                .setLongLived(true)
                .setShortLabel("Shortcut")
                .setLongLabel("Removeable Shortcut")
                .setIcon(icon)
                .setPerson(person)
                .setCategories(setOf("com.jacob.material.bubbles.category.TEXT_SHARE_TARGET"))
                .setIntent(intent)
                .build()
        shortcutManager!!.addDynamicShortcuts(listOf(shortcut))
    }

    private fun addShortcutToNotification(){
        val nm = getSystemService<NotificationManager> (NotificationManager::class.java)
        val nc = nm.getNotificationChannel(NotificationHelper.NOTIFICATION_CHANNEL_ID)

        SystemClock.sleep(5000)


        val ba = nm.areBubblesAllowed()
        val cm = nc.canBubble()

        Log.d("MATERIAL_BOOK", "222 notificationManager.areBubblesAllowed() = $ba")
        Log.d("MATERIAL_BOOK", "222 canBubble = $cm")



//        setUpNotificationChannels()
        notificationHelper.createShortcut()

        val user = Person.Builder().setName("User").build()
        val icon = Icon.createWithResource(this@NotificationActivity, R.drawable.header_006)
        val person = Person.Builder().setName("Person").setIcon(icon).build()

        val pendingIntent = PendingIntent.getActivity(
                this@NotificationActivity,
                0,
                // Launch BubbleActivity as the expanded bubble.
                Intent(this@NotificationActivity, NotificationActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(Uri.parse("https://www.google.com/")),
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        val bubbleMetadata = Notification.BubbleMetadata.Builder(pendingIntent, icon)
                .setDesiredHeight(400)
                .setAutoExpandBubble(true)
                .setSuppressNotification(true)
                .build()


        var style = Notification.MessagingStyle(user)
                        .addMessage(
                                Notification.MessagingStyle.Message(
                                        "Message",
                                        System.currentTimeMillis(),
                                        person
                                )
                        )


        val notification = Notification.Builder(this@NotificationActivity, NotificationHelper.NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_baseline_message)
                .setBubbleMetadata(bubbleMetadata)
                .setStyle(style)
                .setShortcutId("contact_1")
                .build()

        val notificationManager = getSystemService<NotificationManager> (NotificationManager::class.java)
        notificationManager!!.notify(notificationId, notification)
    }







    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}