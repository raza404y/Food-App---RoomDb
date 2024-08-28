package com.example.foodapp.repositories

import androidx.lifecycle.LiveData
import com.example.foodapp.database.User
import com.example.foodapp.database.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }



   fun getUser(id: Int):LiveData<User>{
       return userDao.getUser(id)
   }


}