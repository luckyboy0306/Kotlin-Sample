package com.example.kotlin_sample.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel

open class BaseViewModel :ViewModel(),LifecycleObserver{
    val TAG = this::class.java.simpleName
}