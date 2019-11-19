package com.example.personal

import android.app.Application
import androidx.work.Configuration

class App:Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}