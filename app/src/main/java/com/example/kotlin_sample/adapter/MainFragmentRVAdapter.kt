package com.example.kotlin_sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.*
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kotlin_sample.R
import com.example.kotlin_sample.bean.Data
import com.example.kotlin_sample.bean.MainArticlBean
import com.example.kotlin_sample.databinding.MainGridItemBinding

class MainFragmentRVAdapter() : RecyclerView.Adapter<MainFragmentRVAdapter.MainFragmentRVViewHolder>() {
    var datas :MutableList<MainArticlBean.Data> = ArrayList()
    class MainFragmentRVViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(datas : List<MainArticlBean.Data>){
        this.datas.clear()
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    fun addData(datas:List<MainArticlBean.Data>){
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentRVViewHolder {
        return MainFragmentRVViewHolder(inflate<MainGridItemBinding>(LayoutInflater.from(parent.context),R.layout.main_grid_item,parent,false).root)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MainFragmentRVViewHolder, position: Int) {
        val bind = bind<MainGridItemBinding>(holder.itemView)
        bind?.mainGridItemVM = datas.get(position)
    }
}