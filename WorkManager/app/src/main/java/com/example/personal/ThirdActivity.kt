package com.example.personal

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.delay
import java.time.Duration

class ThirdActivity : AppCompatActivity() {

    private val duration: Duration = Duration.ofHours(1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork(
                        "test_work",
                        ExistingPeriodicWorkPolicy.KEEP,
                        PeriodicWorkRequestBuilder<BackgroundWork>(duration).build())
    }
}


