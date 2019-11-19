package com.example.personal

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.background.R
import java.time.Duration
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
class SecondActivity : AppCompatActivity() {
    private val backWorkRequest by lazy {
        OneTimeWorkRequestBuilder<BackgroundWork>()
                .setConstraints(constraints)
                .build()
    }
    private val periodicWorkRequest by lazy {
        PeriodicWorkRequestBuilder<BackgroundWork>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

    }
    private val constraints by lazy {
        Constraints.Builder()
                .setRequiresCharging(true)
                .build()
    }
    private val workManager by lazy {
        WorkManager.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun startWork(view: View) {
        val backWorkRequest2 = OneTimeWorkRequestBuilder<BackgroundWork>()
                .setConstraints(constraints)
                .build()
        workManager.enqueue(backWorkRequest2)
    }

    fun stopWork(view: View) {
        workManager.cancelAllWork()
//        workManager.cancelWorkById(backWorkRequest.id)
    }

    fun startPeriodicWork(view: View) {
        Log.e("startPeriodicWork","startPeriodicWork")
        workManager.enqueue(periodicWorkRequest)
    }
}
