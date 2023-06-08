package com.example.kotlin_sample.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.kotlin_sample.bean.MainBannerBean
import com.youth.banner.adapter.BannerAdapter

class BannerAdapter(datas:List<MainBannerBean>) : BannerAdapter<MainBannerBean,com.example.kotlin_sample.adapter.BannerAdapter.BannerViewHolder>(datas){

    class BannerViewHolder(itemView:ImageView) : RecyclerView.ViewHolder(itemView){
        var imageView:ImageView = itemView
    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        var imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setPadding(10,10,10,10)
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: MainBannerBean?, position: Int, size: Int) {
        holder?.imageView?.load(data?.imagePath){
            placeholder(android.R.drawable.stat_notify_error)
            //圆角
            transformations(RoundedCornersTransformation(50f))
        }
    }
}
