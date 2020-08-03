package com.jacob.material.android.r.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jacob.material.android.r.notification.NotificationActivity
import com.jacob.material.android.r.databinding.MainActivityBinding
import com.jacob.material.android.r.inset.InsetAniAccountActivity

class MainActivity : AppCompatActivity() {

    private var _binding: MainActivityBinding? = null
    private val binding: MainActivityBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insetAniButton.setOnClickListener {
            val intent = Intent(this, InsetAniAccountActivity::class.java)
            startActivity(intent)
        }

        binding.notificationButton.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}






