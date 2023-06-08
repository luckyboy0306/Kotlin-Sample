package com.example.kotlin_sample.network

import android.util.Log

object NetWorkCallBack {

    val TAG: String = this.javaClass.simpleName
    inline fun load(onStart:()->Unit,onFinish:()->Unit,onError:(String)->Unit){
        try {
            //Log.i(TAG, "登录接口请求开始")
            onStart()
        }catch (e:Exception){
            //Log.i(TAG, "登录接口请求失败${e.toString()}}")
            onError(e.toString())
        }finally {
            //Log.i(TAG, "登录接口请求完成")
            onFinish()
        }
    }

}