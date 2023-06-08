package com.example.kotlin_sample.util

import android.content.Context
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

object MMKVUtil {

    fun initMMKV(context: Context){
        MMKV.initialize(context,MMKVLogLevel.LevelInfo)
    }

    fun putString(key:String,value:String){
        MMKV.defaultMMKV().putString(key,value)
    }
    fun putInt(key:String,value:Int){
        MMKV.defaultMMKV().putInt(key,value)
    }
    fun putBoolean(key:String,value:Boolean){
        MMKV.defaultMMKV().putBoolean(key,value)
    }
    fun putFloat(key:String,value:Float){
        MMKV.defaultMMKV().putFloat(key,value)
    }
    fun putLong(key:String,value:Long){
        MMKV.defaultMMKV().putLong(key,value)
    }
    fun putBytes(key:String,value:ByteArray){
        MMKV.defaultMMKV().putBytes(key,value)
    }

}