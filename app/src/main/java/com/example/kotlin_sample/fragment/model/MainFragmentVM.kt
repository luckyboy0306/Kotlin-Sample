package com.example.kotlin_sample.fragment.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlin_sample.bean.*
import com.example.kotlin_sample.network.Api
import com.example.kotlin_sample.network.NetWorkCallBack
import com.example.kotlin_sample.network.NetWorkUtil
import com.example.kotlin_sample.util.LiveDataBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainFragmentVM :ViewModel() {
    private val TAG = this::class.java.simpleName
    init {
        getBanner()
        getProjectItem()
    }

    private fun getProjectItem() {
        CoroutineScope(Dispatchers.IO).launch {
            flow<MainArticlBean> {
                NetWorkCallBack.load(
                    onStart = { emit(NetWorkUtil.create<Api>().getArticleList(1).data)},
                    onError = { Log.i(TAG, "获取 项目列表 error = $it") },
                    onFinish = { Log.i(TAG, "获取 项目列表 完成") }
                )
            }.collect(){
                LiveDataBus.get().with("projectItem",MainArticlBean::class.java).postValue(it)
            }
        }
    }



    private fun getBanner() {
        CoroutineScope(Dispatchers.IO).launch {
            flow<List<MainBannerBean>> {
                NetWorkCallBack.load(
                    onStart = { emit(NetWorkUtil.create<Api>().getBanner().data)},
                    onError = { Log.i(TAG, "获取 banner error = $it", ) },
                    onFinish = { Log.i(TAG, "获取 banner 完成") }
                )
            }.collect(){
                LiveDataBus.get().with("banner",List::class.java).postValue(it)
            }
        }
    }

    /**
     * 切换 tab
     */
    fun switchTab(position:Int){
        when(position){
            0->{
                getProjectItem()
            }
            1->{

            }
            2->{
                getSquareList()
            }
        }

    }

    /**
     * 获取广场列表
     */
    private fun getSquareList() {
        CoroutineScope(Dispatchers.IO).launch {
            flow<MainArticlBean> {
                NetWorkCallBack.load(
                    onStart = {
                        emit(NetWorkUtil.create<Api>().getSquareList(1).data)
                    },
                    onError = {

                    },
                    onFinish = {

                    }
                )
            }.collect(){
                LiveDataBus.get().with("projectItem",MainArticlBean::class.java).postValue(it)
            }
        }
    }


    interface MainFragmentVMCallBack{
        fun openDrawer()
    }

}