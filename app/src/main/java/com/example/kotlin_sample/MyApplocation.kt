package com.example.kotlin_sample

import android.app.Application
import com.example.kotlin_sample.network.NetWorkUtil
import com.example.kotlin_sample.util.MMKVUtil
import com.tencent.mmkv.MMKV

class MyApplocation : Application() {
    override fun onCreate() {
        super.onCreate()
        initConfig()
    }

    private fun initConfig() {
        MMKVUtil.initMMKV(this)
    }
}