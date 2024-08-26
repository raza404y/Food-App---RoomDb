package com.example.foodapp.adapters

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.database.Food
import com.example.foodapp.databinding.OrderRvLayoutBinding
import com.example.foodapp.viewmodels.OrderViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class OrderAdapter(private val context: Context, private val orderViewModel: OrderViewModel) :
    ListAdapter<Food, OrderAdapter.ViewHolder>(diffCallBack) {
    inner class ViewHolder(val binding: OrderRvLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(OrderRvLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foodOrderItem = getItem(position)
        val bitmap = BitmapFactory.decodeByteArray(
            foodOrderItem.foodImage,
            0,
            foodOrderItem.foodImage!!.size
        )
        setData(holder, bitmap, foodOrderItem)

        holder.binding.deleteOrderBtn.setOnClickListener {
            deleteOrder(foodOrderItem)
        }

    }

    private fun deleteOrder(foodOrderItem: Food?) {
        val dialog = MaterialAlertDialogBuilder(context)
        dialog.apply {
            setTitle("Delete")
            setMessage("Do you want delete order?")
                .setPositiveButton("Yes") { dialogInterface, _ ->
                    foodOrderItem?.let {
                        orderViewModel.delete(it) {
                            Toast.makeText(context, "Order deleted!", Toast.LENGTH_SHORT).show()
                            dialogInterface.dismiss()
                        }
                    }
                }
                .setNegativeButton("No") { d, _ ->
                    d.dismiss()
                }
                .show()
        }
    }

    private fun setData(holder: ViewHolder, bitmap: Bitmap?, foodOrderItem: Food) {
        holder.apply {
            binding.orderImage.setImageBitmap(bitmap)
            binding.orderNumber.text = foodOrderItem.id.toString()
            binding.orderName.text = foodOrderItem.name
            binding.foodDescription.text = "Quantity: ${foodOrderItem.quantity}  -  "
            binding.orderPrice.text = "$${foodOrderItem.price}"
            binding.orderPhoneNum.text = "Phone No. ${foodOrderItem.phone}"
            binding.textView.text = foodOrderItem.orderTime
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }

        }
    }
}