package com.example.loginapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.JournalMode
import com.example.loginapp.BuildConfig
import com.example.loginapp.data.RoomDatabase
import com.example.loginapp.data.remote.middleware.AddHeaderInterceptor
import com.example.loginapp.data.remote.middleware.RxErrorHandlingCallAdapterFactory
import com.example.loginapp.util.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    companion object {
        private const val ENDPOINT_URL = "http://private-222d3-homework5.apiary-mock.com"
    }

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): RoomDatabase {
        return Room.databaseBuilder(context, RoomDatabase::class.java, BuildConfig.DATABASE_NAME)
                .setJournalMode(JournalMode.TRUNCATE)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: AddHeaderInterceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
                .readTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(Constants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(interceptor)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            httpClientBuilder.addInterceptor(logging)
            logging.level = HttpLoggingInterceptor.Level.BODY
        }
        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .baseUrl(ENDPOINT_URL)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideAddHeaderInterceptor() = AddHeaderInterceptor()

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().create()
}