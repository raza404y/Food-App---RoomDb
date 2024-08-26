package com.example.foodapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.foodapp.database.Food
import com.example.foodapp.database.FoodDatabase
import com.example.foodapp.repositories.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = FoodDatabase.createDatabase(application).getFoodDao()
    private val repository: Repository = Repository(dao)
    val foodList: LiveData<List<Food>> = repository.getAllOrders()

    fun insert(food: Food,onInserted:()->Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(food)
        withContext(Dispatchers.Main) {
            onInserted()
        }
    }

    fun update(food: Food) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(food)
    }

    fun delete(food: Food,onDeleted:()->Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(food)
        withContext(Dispatchers.Main){
            onDeleted()
        }
    }

}