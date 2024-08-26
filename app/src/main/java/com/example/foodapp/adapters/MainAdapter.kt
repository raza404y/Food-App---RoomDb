package com.example.foodapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.models.FoodModel
import com.example.foodapp.databinding.MainRvLayoutBinding
import com.example.foodapp.ui.DetailsActivity

class MainAdapter(private val context: Context, private val list: ArrayList<FoodModel>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MainRvLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(food: FoodModel) {
            food.apply{
                binding.orderImage.setImageResource(foodImage)
                binding.orderName.text = foodName
                binding.Price.text = price.toString()
                binding.foodDescription.text = description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MainRvLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodItem = list[position]
        holder.setData(foodItem)

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,DetailsActivity::class.java).apply {
                putExtra("food",foodItem)
            })
        }
    }
}
