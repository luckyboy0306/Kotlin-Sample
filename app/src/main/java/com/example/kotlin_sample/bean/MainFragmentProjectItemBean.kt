package com.example.kotlin_sample.bean

import com.example.kotlin_sample.bean.Data

data class MainFragmentProjectItemBean(
    val curPage: Int,
    val datas: List<Data>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)