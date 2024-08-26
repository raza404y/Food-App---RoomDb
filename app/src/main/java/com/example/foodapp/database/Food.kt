package com.example.foodapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "Name")
    var name: String?,

    @ColumnInfo(name = "Price")
    var price: Int?,

    @ColumnInfo(name = "Description")
    var description: String?,

    @ColumnInfo(name = "orderTime")
    var orderTime: String?,

    @ColumnInfo(name = "Image")
    var foodImage: ByteArray? = null
)
