package com.example.loginapp.data.authentication

import androidx.room.Dao
import androidx.room.Query
import com.example.loginapp.data.common.BaseDao
import com.example.loginapp.domain.authentication.entity.User
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface UserDao: BaseDao<User> {

    @Query("SELECT * FROM ${User.TABLE_NAME} LIMIT 1")
    fun getFirst(): Single<User>

    @Query("DELETE FROM ${User.TABLE_NAME}")
    override fun deleteAll()

    @Query("SELECT * FROM ${User.TABLE_NAME} WHERE ${User.COLUMN_ID} = :id")
    override fun getItem(id: Long): Maybe<User>

    @Query("SELECT * FROM ${User.TABLE_NAME}")
    override fun getItems(): Single<List<User>>
}