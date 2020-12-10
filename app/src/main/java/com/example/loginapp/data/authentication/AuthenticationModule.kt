package com.example.loginapp.data.authentication

import com.example.loginapp.data.RoomDatabase
import com.example.loginapp.data.authentication.repository.AuthenticationDataRepository
import com.example.loginapp.di.AppModule
import com.example.loginapp.domain.authentication.AuthenticationRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class AuthenticationModule {

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): AuthenticationApi {
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDao(database: RoomDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideRepository(api: AuthenticationApi, dao: UserDao): AuthenticationRepository {
        return AuthenticationDataRepository(api, dao)
    }

}