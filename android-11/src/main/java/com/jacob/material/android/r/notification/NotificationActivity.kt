package com.jacob.material.android.r.notification

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.jacob.material.android.r.databinding.NotificationActivityBinding

class NotificationActivity : AppCompatActivity() {

    private var _binding: NotificationActivityBinding? = null
    private val binding: NotificationActivityBinding get() = _binding!!

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
                        binding.shortcutInfoTextView.text = "Shortcut of ${contacts[0].description}"
                        binding.shortcutInfoTextView.setTextColor(Color.RED)
                    }else if(NotificationContact.URI_CATEGORY_PENDING.equals(category)){
                        binding.pendingInfoTextView.text = "Pending of ${contacts[0].description}"
                        binding.pendingInfoTextView.setTextColor(Color.RED)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}