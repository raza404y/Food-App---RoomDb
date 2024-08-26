package com.example.foodapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.adapters.MainAdapter
import com.example.foodapp.models.FoodModel
import com.example.foodapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val list = ArrayList<FoodModel>()
        setData(list)

        binding.actionBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity,OrderActivity::class.java))
        }

    }

    private fun setData(list: ArrayList<FoodModel>) {
        list.add(FoodModel(R.drawable.img, "Shawarma", 4, "Famous chinese Shawarma is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_9, "Soft Drinks", 15, "Your favourite Soft Drinks are available with 20% discount."))
        list.add(FoodModel(R.drawable.img_11, "Noodles", 3, "Chinese Noodle available with 50% discount."))
        list.add(FoodModel(R.drawable.img_8, "Tacos", 5, "Mexico Tacos are available with 20% discount."))
        list.add(FoodModel(R.drawable.img_4, "Sandwich", 4, "French Sandwich is available with 10% discount."))
        list.add(FoodModel(R.drawable.img_5, "Milkshake", 5, "Most favourite Milkshake is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_10, "Onion Ring", 1, "Onion Ring is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_6, "Muffins", 11, "Muffin is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_7, "Burritos", 9, "Most famous Burger is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_3, "Burger", 1, "Burger is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_12, "Pancake", 3, "Pancake is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_5, "Milkshake", 5, "Most favourite Milkshake is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_6, "Muffins", 11, "Muffin is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_7, "Burritos", 9, "Most famous Burger is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_7, "Burritos", 9, "Most famous Burger is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_3, "Burger", 1, "Burger is available with 20% discount."))
        list.add(FoodModel(R.drawable.img_12, "Pancake", 3, "Pancake is available with 20% discount."))



        val adapter = MainAdapter(this@MainActivity,list)
        binding.mainRV.adapter = adapter
        binding.mainRV.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
//        binding.mainRV.addItemDecoration(DividerItemDecoration(this@MainActivity,RecyclerView.VERTICAL))
    }

}