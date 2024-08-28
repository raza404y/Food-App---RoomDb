package com.example.foodapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodapp.Constants
import com.example.foodapp.R
import com.example.foodapp.database.User
import com.example.foodapp.databinding.ActivityLoginBinding
import com.example.foodapp.viewmodels.UserViewModel

class Login : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private var user: User? = null
    private lateinit var userViewModel: UserViewModel
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
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

        userViewModel.getUser(Constants.USER_ID).observe(this, Observer {
        if (it != null)
            user = it
        })

        binding.btnLogin.setOnClickListener {
            signInUser()
        }

        val sharedPreferences = getSharedPreferences("sh", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLogged",false)

        if (isLoggedIn){
            startActivity(Intent(this@Login,MainActivity::class.java))
            finish()
        }

        binding.createNewAcc.setOnClickListener {
            startActivity(Intent(this@Login,SignUp::class.java))
        }

    }

    private fun signInUser() {
        val email = binding.emailEt.text.toString().trim()
        val password = binding.passwordNumberET.text.toString().trim()



        if (user != null) {
            if (email.isEmpty()) {
                binding.emailEt.error = "Email"
                return
            } else if (password.isEmpty()) {
                binding.passwordNumberET.error = "Password"
                return
            } else if (email == user?.email && password == user?.password) {

                val sharedPreferences = getSharedPreferences("sh", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isLogged",true)
                editor.apply()

                Toast.makeText(this@Login, "Login Successfully", Toast.LENGTH_SHORT).show()
                startActivity((Intent(this@Login, MainActivity::class.java)))
                finish()
            } else {
                Toast.makeText(this@Login, "Email or Password Wrong", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this@Login, "Account not found", Toast.LENGTH_SHORT).show()
        }
    }
}