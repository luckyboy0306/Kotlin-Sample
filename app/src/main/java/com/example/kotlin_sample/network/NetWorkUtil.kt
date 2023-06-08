package com.example.kotlin_sample.network

import com.tencent.mmkv.MMKV
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NetWorkUtil {

    //单例模式
    val instance :Retrofit by lazy {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        okHttpClient.cookieJar(object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                if (url == "https://www.wanandroid.com/user/register".toHttpUrlOrNull()) {
                    return
                }
                MMKV.defaultMMKV().putStringSet("cookies", cookies.map { it.toString() }.toSet())
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                if (url == "https://www.wanandroid.com/user/login".toHttpUrlOrNull() || url == "https://www.wanandroid.com/user/register".toHttpUrlOrNull()) {
                    return arrayListOf()
                }
                val cookies = arrayListOf<Cookie>()
                MMKV.defaultMMKV().getStringSet("cookies", setOf<String>())?.forEach {
                    cookies.add(Cookie.parse(url, it)!!)
                }
                return cookies
            }

        })
        Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client( okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    init {
        println("网络框架初始化")
    }

    /**
     * 发起请求
     */
    inline fun <reified T> create(): T {
        return instance.create()
    }


}