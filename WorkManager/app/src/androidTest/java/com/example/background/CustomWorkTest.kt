package com.example.background

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.ListenableWorker
import androidx.work.await
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.TestWorkerBuilder
import androidx.work.workDataOf
import com.example.personal.TestCoroutineWork
import com.example.personal.TestWork
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

//@SmallTest
@RunWith(AndroidJUnit4::class)
class CustomWorkTest {

    private  var mContext: Context = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun testCoroutineWorkerTest(){
        runBlocking {
            val work = TestListenableWorkerBuilder<TestCoroutineWork>(mContext)
                    .setInputData(workDataOf("key" to 10))
                    .setRunAttemptCount(2)
                    .build()
            val result = work.startWork().await()
            Assert.assertEquals(result,ListenableWorker.Result.success())
        }
    }


    @Test
    fun testWorkTest(){
        runBlocking {
            val executor = Executors.newSingleThreadExecutor()
            val work = TestWorkerBuilder<TestWork>(context = mContext,executor = executor)
                    .setInputData(workDataOf("key" to 10))
                    .setRunAttemptCount(2)
                    .build()
            val result = work.startWork().await()
            Assert.assertEquals(result,ListenableWorker.Result.success())

        }
    }



}