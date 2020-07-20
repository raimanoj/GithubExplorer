package com.manojrai.androidgithubexplorer

import android.app.Application
import com.manojrai.androidgithubexplorer.di.appModule
import com.manojrai.androidgithubexplorer.di.contributorsModule
import com.manojrai.androidgithubexplorer.di.mainModule
import com.manojrai.androidgithubexplorer.di.repoOfContributorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(listOf(appModule, mainModule, contributorsModule, repoOfContributorModule))
        }
    }
}