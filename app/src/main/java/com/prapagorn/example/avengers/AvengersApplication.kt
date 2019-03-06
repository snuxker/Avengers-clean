package com.prapagorn.example.avengers

import android.app.Application
import com.prapagorn.example.avengers.di.*
import org.koin.android.ext.android.startKoin

class AvengersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this,
            listOf(apiModule,
                networkModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule,
                utilityModule))
    }
}