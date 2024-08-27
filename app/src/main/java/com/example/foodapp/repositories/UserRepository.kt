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

    suspend fun delete(user: User){
        userDao.deleteUser(user)
    }

   fun getUser(id: Int):LiveData<User>{
       return userDao.getUser(id)
   }

   suspend fun emailAlreadyExist(email: String):Long {
      return withContext(Dispatchers.IO) {
           userDao.emailAlreadyExist(email)
       }
   }

}