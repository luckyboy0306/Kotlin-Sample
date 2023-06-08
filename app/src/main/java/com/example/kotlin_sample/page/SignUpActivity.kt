package com.example.kotlin_sample.page

import android.os.Bundle
import com.example.kotlin_sample.R
import com.example.kotlin_sample.base.BaseActivity
import com.example.kotlin_sample.databinding.ActivitySignUpBinding
import com.example.kotlin_sample.modle.SignUpViewModel

class SignUpActivity(override var contentView: ActivitySignUpBinding) : BaseActivity<ActivitySignUpBinding>() {
    override fun getRes(): Int? {
        return R.layout.activity_sign_up
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView.signUpViewModel = SignUpViewModel()
    }
}