package com.example.foodapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.database.Food
import com.example.foodapp.databinding.ActivityDetailsBinding
import com.example.foodapp.models.FoodModel
import com.example.foodapp.viewmodels.OrderViewModel
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailsActivity : AppCompatActivity() {
    private val binding: ActivityDetailsBinding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    private lateinit var orderViewModel: OrderViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        orderViewModel = ViewModelProvider(this@DetailsActivity)[OrderViewModel::class.java]

        val foodData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("food", FoodModel::class.java)
        } else {
            intent.getSerializableExtra("food") as FoodModel
        }

        setFoodData(foodData)

        val price = binding.detailprice23.text.toString().toInt()
        var count: Int = 1
        binding.increment.setOnClickListener {
            count++
            binding.counter.text = count.toString()
            binding.detailprice23.text = (count * price).toString()
        }
        binding.decrement.setOnClickListener {
            if (count > 1) count--
            binding.counter.text = count.toString()
            binding.detailprice23.text = (count * price).toString()
        }

        binding.submitBtn.setOnClickListener {
            saveOrderInDatabase()
        }



    }

    @SuppressLint("SetTextI18n")
    private fun saveOrderInDatabase() {
        val bitmap = (binding.detailImage.getDrawable() as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val imageArray = stream.toByteArray()

        val timeFormat = SimpleDateFormat("E hh:mm:ss a", Locale.getDefault())
        val currentTime = timeFormat.format(Date())
        val customerName = binding.userName.text.toString().trim()
        val phoneNo = binding.userPhoneNumer.text.toString().trim()
        if (customerName.isEmpty()) {
            showToast("Enter name")
        } else if (phoneNo.isEmpty() || phoneNo.length < 4 || phoneNo.length > 10) {
            showToast("Phone is empty or invalid")
        }else {

            val food = Food(
                name = binding.detailName.text.toString(),
                price = binding.detailprice23.text.toString().toInt(),
                description = binding.detailDes.text.toString(),
                orderTime = currentTime,
                quantity = binding.counter.text.toString().toInt(),
                customerName = customerName,
                phone = phoneNo,
                foodImage = imageArray
            )

            orderViewModel.insert(food) {
                showToast("Order Submitted")
                binding.userName.setText("")
                binding.userPhoneNumer.setText("")
                startActivity(Intent(this@DetailsActivity, OrderActivity::class.java))
            }
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

    private fun showToast(message: String) {
        Toast.makeText(this@DetailsActivity, message, Toast.LENGTH_SHORT).show()
    }
}
