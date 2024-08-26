package com.example.foodapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapters.OrderAdapter
import com.example.foodapp.databinding.ActivityOrderBinding
import com.example.foodapp.viewmodels.OrderViewModel

class OrderActivity : AppCompatActivity() {
    private val binding: ActivityOrderBinding by lazy {
        ActivityOrderBinding.inflate(layoutInflater)
    }
    private lateinit var orderViewModel: OrderViewModel
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        orderViewModel = ViewModelProvider(this@OrderActivity)[OrderViewModel::class.java]

        binding.orderRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = OrderAdapter(this@OrderActivity,orderViewModel)
        binding.orderRecyclerView.adapter = adapter

        orderViewModel.foodList.observe(this, Observer { foodList ->
            adapter.submitList(foodList)
            binding.orderRecyclerView.post {
                binding.orderRecyclerView.smoothScrollToPosition(0)
            }
        })

    }
}