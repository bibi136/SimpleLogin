package com.example.loginapp.di

import com.example.loginapp.data.authentication.AuthenticationModule
import com.example.loginapp.ui.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthenticationModule::class])
interface ViewModelFactoryComponent {
    
    fun inject(target: ViewModelFactory)
}