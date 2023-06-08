package com.example.kotlin_sample.base

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T> : AppCompatActivity() {

    val TAG = this::class.java.simpleName
    abstract var contentView: T

    open fun getRes():Int?{
        return null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel(getRes())
    }
    open fun initViewModel(layout : Int?){
        Log.i(TAG, "initViewModel: $layout")
        if (layout!=null) {
            contentView = setContentView(this, layout)
        } else{
            throw Exception("layout is null")
        }
    }

}