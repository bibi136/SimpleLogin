package com.example.loginapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginapp.BuildConfig
import com.example.loginapp.data.authentication.UserDao
import com.example.loginapp.domain.authentication.entity.User

@Database(
    entities = [User::class],
    version = BuildConfig.DATABASE_VERSION
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}