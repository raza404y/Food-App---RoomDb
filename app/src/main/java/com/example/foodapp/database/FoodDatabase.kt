package com.example.foodapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class, User::class], version = 3, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
    abstract fun getUserDao(): UserDao

    companion object {
        private var INSTANCE: FoodDatabase? = null
        fun createDatabase(context: Context): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "food_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}