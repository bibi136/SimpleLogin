package com.example.loginapp.ui.app

import android.app.Application
import com.example.loginapp.di.AppModule
import com.example.loginapp.di.DaggerViewModelFactoryComponent
import com.example.loginapp.di.ViewModelFactoryComponent

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var viewModelFactoryComponent: ViewModelFactoryComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        viewModelFactoryComponent = DaggerViewModelFactoryComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}