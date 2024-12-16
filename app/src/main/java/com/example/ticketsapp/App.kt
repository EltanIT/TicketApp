package com.example.ticketsapp

import android.app.Application
import com.example.core.di.apiModule
import com.example.core.di.authModule
import com.example.core.di.authorModule
import com.example.core.di.departmentModule
import com.example.core.di.executorModule
import com.example.core.di.problemTypeModule
import com.example.core.di.ticketModule
import com.example.core.di.useCaseModule
import com.example.core.di.userModule
import com.example.ticketsapp.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            androidLogger(Level.DEBUG)
            modules(
                authModule,
                viewModelsModule,
                useCaseModule,
                apiModule,
                departmentModule,
                userModule,
                problemTypeModule,
                ticketModule,
                executorModule,
                authorModule
            )
        }
    }
}