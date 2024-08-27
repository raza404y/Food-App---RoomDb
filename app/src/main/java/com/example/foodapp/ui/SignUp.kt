package com.example.foodapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.Constants
import com.example.foodapp.R
import com.example.foodapp.database.User
import com.example.foodapp.databinding.ActivitySignUpBinding
import com.example.foodapp.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUp : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    private lateinit var userViewModel: UserViewModel
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

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        binding.btnSignUp.setOnClickListener {
            createAccount()
        }

        binding.alreadyHaveAcc.setOnClickListener {
            startActivity(Intent(this@SignUp,Login::class.java))
        }

    }

    private fun createAccount() {
        val email = binding.emailET.text.toString().trim()
        val password = binding.passwordNumberET.text.toString().trim()
        val confirmPassword = binding.confirmNumberPassword.text.toString().trim()

        if (email.isEmpty()) {
            binding.emailET.error = "Enter email"
            return
        } else if (password.isEmpty()) {
            binding.passwordNumberET.error = "Enter password"
            return
        } else if (binding.passwordNumberET.length() < 3) {
            binding.passwordNumberET.error = "Password too short"
            return
        } else if (confirmPassword.isEmpty()) {
            binding.confirmNumberPassword.error = "Confirm Password"
            return
        } else if (password != confirmPassword) {
            binding.confirmNumberPassword.error = "Password not matching"
            return
        }

        // Use a coroutine to check if the email already exists
        CoroutineScope(Dispatchers.IO).launch {
            val count = userViewModel.emailAlreadyExist(email)
            if (count>0) {
                showToast("Email Already Exist")
            } else {
                val user = User(
                    id = Constants.USER_ID,
                    email = email,
                    password = password,
                    isRegistered = true
                )
                userViewModel.insertUser(user) {
                    showToast("Account Created")
                    startActivity(Intent(this@SignUp, Login::class.java))
                    finish()
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@SignUp, message, Toast.LENGTH_SHORT).show()
    }
}