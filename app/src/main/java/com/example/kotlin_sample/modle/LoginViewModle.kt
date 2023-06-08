package com.example.kotlin_sample.modle

import android.util.Log
import androidx.lifecycle.*
import com.example.kotlin_sample.base.BaseViewModel
import com.example.kotlin_sample.bean.MyResponse
import com.example.kotlin_sample.network.Api
import com.example.kotlin_sample.network.NetWorkCallBack
import com.example.kotlin_sample.network.NetWorkUtil
import com.example.kotlin_sample.util.LiveDataBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModle :BaseViewModel() {

    var name : MutableLiveData<String> = MutableLiveData("LuckyBoy123")
    var password : MutableLiveData<String> = MutableLiveData("1234568x")


    fun login() {
        CoroutineScope(Dispatchers.IO).launch {
            NetWorkCallBack.load(
                onStart = {
                    val login = NetWorkUtil.create<Api>().login(name.value!!,password.value!!)
                    Log.i(TAG, "login: $login")
                    LiveDataBus.get().with("key_test",MyResponse::class.java).postValue(login)
                },
                onFinish = {

                },
                onError = {

                },
            )
        }

    }

    fun register() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        Log.i("TAG", "onDestroy: ")
    }

}