package com.example.foodapp.repositories

import androidx.lifecycle.LiveData
import com.example.foodapp.database.Food
import com.example.foodapp.database.FoodDao

class Repository(private val foodDao: FoodDao) {

    suspend fun insert(food: Food){
        foodDao.insert(food)
    }

    suspend fun update(food: Food){
        foodDao.update(food)
    }

    suspend fun delete(food: Food){
        foodDao.delete(food)
    }

    fun getAllOrders():LiveData<List<Food>>{
        return foodDao.getAllOrders()
    }

}