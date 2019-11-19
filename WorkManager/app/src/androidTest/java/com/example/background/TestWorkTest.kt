package com.example.background

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.*
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.personal.TestWork
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Testing with constraints,delay...
 */
@RunWith(AndroidJUnit4::class)
class TestWorkTest {


    private var mContext: Context = InstrumentationRegistry.getInstrumentation().context

    /**
     * 在测试模式下初始化 WorkManager
     */
    @Before
    fun setUp() {
        val configuration = Configuration.Builder().build()
        WorkManagerTestInitHelper.initializeTestWorkManager(mContext, configuration)
    }

    @Test
    fun testWorkWithConstraints() {
        val constraints: Constraints = TODO("Define Constraints")
        val request = OneTimeWorkRequestBuilder<TestWork>()
                .setConstraints(constraints)
                .build()
        WorkManager.getInstance(mContext).equals(request)
        val dirver = WorkManagerTestInitHelper.getTestDriver(mContext)
        dirver?.setAllConstraintsMet(request.id)
        val workInfo = WorkManager.getInstance(mContext).getWorkInfoById(request.id).get()
        Assert.assertEquals(workInfo.state, WorkInfo.State.SUCCEEDED)

    }
}