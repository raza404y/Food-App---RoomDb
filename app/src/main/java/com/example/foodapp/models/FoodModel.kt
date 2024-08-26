package com.example.foodapp.models

import java.io.Serializable

data class FoodModel(
    var foodImage: Int,
    var foodName: String,
    var price: Int,
    var description: String
):Serializable
