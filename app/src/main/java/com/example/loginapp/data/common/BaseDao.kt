package com.example.loginapp.data.common

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import io.reactivex.Maybe
import io.reactivex.Single

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(region: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>): List<Long>

    @Update
    fun update(items: T)

    @Update
    fun update(items: List<T>)

    @Delete
    fun delete(item: T)

    @Delete
    fun delete(items: List<T>)

    fun deleteAll()

    fun getItem(id: Long): Maybe<T>

    fun getItems(): Single<List<T>>
}