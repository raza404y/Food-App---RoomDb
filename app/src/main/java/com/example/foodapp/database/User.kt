package com.example.foodapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodapp.Constants

@Entity(tableName = "User")
data class User(

    @PrimaryKey(autoGenerate = false)
    var id: Int = Constants.USER_ID,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "password")
    var password: String?,

    @ColumnInfo(name = "isRegistered")
    var isRegistered: Boolean = false

)
