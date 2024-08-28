package com.example.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.database.FoodDatabase
import com.example.foodapp.database.User
import com.example.foodapp.database.UserDao
import com.example.foodapp.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(application: Application):AndroidViewModel(application) {

    private val dao: UserDao = FoodDatabase.createDatabase(application).getUserDao()
    private var repository: UserRepository = UserRepository(dao)

    fun insertUser(user: User,onAccountCreated:()->Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertUser(user)
        withContext(Dispatchers.Main){
            onAccountCreated()
        }
    }

    fun getUser(id:Int):LiveData<User>{
        return repository.getUser(id)
    }

}