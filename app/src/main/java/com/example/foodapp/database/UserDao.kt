package com.example.foodapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT COUNT(*) FROM User WHERE email = :email")
   suspend fun emailAlreadyExist(email: String):Long

    @Query("select * from User where id =:id")
    fun getUser(id: Int):LiveData<User>

}