package com.example.loginapp.domain.authentication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.loginapp.domain.authentication.entity.User.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class User(
    @ColumnInfo(name = COLUMN_ID) @PrimaryKey val id: Long,
    @ColumnInfo(name = COLUMN_NAME) val name: String,
    @ColumnInfo(name = COLUMN_TOKEN) val token: String
) {

    companion object {
        const val TABLE_NAME = "User"
        const val COLUMN_NAME = "name"
        const val COLUMN_TOKEN = "token"
        const val COLUMN_ID = "id"
    }
}