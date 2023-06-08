package com.example.kotlin_sample.fragment.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlin_sample.bean.ProjectTreeBean
import com.example.kotlin_sample.network.Api
import com.example.kotlin_sample.network.NetWorkCallBack
import com.example.kotlin_sample.network.NetWorkUtil
import com.example.kotlin_sample.util.LiveDataBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ServerFragmentVM : ViewModel() {

    private val TAG = this::class.java.simpleName

    init {
        getProjectTree()
    }
    private fun getProjectTree() {

        CoroutineScope(Dispatchers.IO).launch {
            flow<List<ProjectTreeBean>> {
                NetWorkCallBack.load(
                    onStart = {
                        emit(NetWorkUtil.create<Api>().getProjectTree().data)
                    },
                    onFinish = {
                        Log.i(TAG, "获取 项目分类 完成")
                    },
                    onError = {
                        Log.i(TAG, "获取 项目分类 error = $it")
                    }
                )
            }.collect(){
                LiveDataBus.get().with("projectTree",List::class.java).postValue(it)

            }
        }

    }

}