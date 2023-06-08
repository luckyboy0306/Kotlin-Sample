package com.example.kotlin_sample.network

import com.example.kotlin_sample.bean.*
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username: String,@Field("password") password: String): MyResponse<Any>

    /**
     * 注册
     */
    @POST("user/register")
    suspend fun register(username: String, password: String, repassword: String): MyResponse<RegisterBean>

    /**
     * 首页 banner 获取
     */
    @GET("banner/json")
    suspend fun getBanner(): MyResponse<List<MainBannerBean>>

    /**
     * 首页文章列表
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page:Int): MyResponse<MainArticlBean>

    /**
     * 导航数据
     */
    @GET("navi/json")
    suspend fun getNavigation(): MyResponse<Any>

    /**
     * 项目分类
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): MyResponse<List<ProjectTreeBean>>

    /**
     * 项目列表数据
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page:Int,@Query("cid") cid:Int): MyResponse<MainFragmentProjectItemBean>

    /**
     * 广场数据
     */

    @GET("user_article/list/{page}/json")
    suspend fun getSquareList(@Path("page") page:Int): MyResponse<MainArticlBean>


}