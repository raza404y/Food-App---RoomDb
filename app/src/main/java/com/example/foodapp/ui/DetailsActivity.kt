package com.example.foodapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.models.FoodModel
import com.example.foodapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private val binding: ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val foodData = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra("food", FoodModel::class.java)
        }else{
            intent.getSerializableExtra("food") as FoodModel
        }

        setFoodData(foodData)

       var count: Int = 1
        binding.increment.setOnClickListener {
            count++
            binding.counter.text = count.toString()
        }
        binding.decrement.setOnClickListener {
            if (count > 1) count--
            binding.counter.text = count.toString()
        }

    }

    private fun setFoodData(foodData: FoodModel?) {
        foodData?.apply {
            binding.detailImage.setImageResource(foodImage)
            binding.detailName.text = foodName
            binding.detailDes.text = description
            binding.detailprice23.text = price.toString()
        }
    }
}