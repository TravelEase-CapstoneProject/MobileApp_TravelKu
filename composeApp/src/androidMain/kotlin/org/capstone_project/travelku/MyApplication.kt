package org.capstone_project.travelku

import android.app.Application
import org.capstone_project.travelku.ui.presentation.initializeKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class MyApplication : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidLogger()
            androidContext(this@MyApplication)
        }
    }
}