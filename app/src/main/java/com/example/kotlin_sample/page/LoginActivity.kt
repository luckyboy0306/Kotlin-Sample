package com.example.kotlin_sample.page

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.kotlin_sample.R
import com.example.kotlin_sample.bean.MyResponse
import com.example.kotlin_sample.databinding.ActivityLoginBinding
import com.example.kotlin_sample.modle.LoginViewModle
import com.example.kotlin_sample.util.LiveDataBus

class LoginActivity : AppCompatActivity() {

    lateinit var contentView: ActivityLoginBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView = setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        val loginViewModle = LoginViewModle()
        contentView.loginViewModel = loginViewModle
        lifecycle.addObserver(loginViewModle)
        LiveDataBus.get().with("key_test",MyResponse::class.java).observe(this) {
            if (it.errorCode != 0) {
                Toast.makeText(this, it.errorMsg, Toast.LENGTH_LONG).show()
            } else {
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


    }

}