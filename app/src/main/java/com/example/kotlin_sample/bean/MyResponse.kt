package com.example.kotlin_sample.bean

data class MyResponse<T> (var data:T,var errorCode :Int,var errorMsg:String)