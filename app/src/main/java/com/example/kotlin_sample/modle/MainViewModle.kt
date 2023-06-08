package com.example.kotlin_sample.modle

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlin_sample.base.BaseViewModel
import com.example.kotlin_sample.bean.MainBannerBean
import com.example.kotlin_sample.network.Api
import com.example.kotlin_sample.network.NetWorkCallBack
import com.example.kotlin_sample.network.NetWorkUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.create

class MainViewModle : BaseViewModel() {

}