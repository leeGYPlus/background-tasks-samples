package com.example.personal

import android.content.Context
import android.util.Log
import android.util.LruCache
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay


class BackgroundWork(appContext: Context, workParams: WorkerParameters) : Worker(appContext, workParams) {
    override fun doWork(): Result {
        Log.e("doWork","time start")
        Thread.sleep(1000L)
        Log.e("doWork","time end")
        return Result.success(workDataOf("name" to "success"))

    }
}


class TestCoroutineWork(context: Context,parameters: WorkerParameters): CoroutineWorker(context,parameters){
    override suspend fun doWork(): Result {
        Log.d("TAG","Running TestWork")
        delay(1000)
        return Result.success()
    }
}

class TestWork(context: Context,parameters: WorkerParameters): Worker(context,parameters){
    override  fun doWork(): Result {
        Log.d("TAG","Running TestWork")
        return Result.success()
    }
}