package com.example.kotlin_sample.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.kotlin_sample.fragment.MainFragment
import com.example.kotlin_sample.fragment.MineFragment
import com.example.kotlin_sample.fragment.NewsFragment
import com.example.kotlin_sample.fragment.ServerFragment

class MainFragmentAdapter(fragmentActivity: FragmentActivity) :FragmentStateAdapter(fragmentActivity) {

    var fragments : ArrayList<Fragment> = ArrayList()
    init {
        fragments.add(MainFragment.newInstance("首页","main"))
        fragments.add(ServerFragment.newInstance("服务"," server"))
        fragments.add(NewsFragment.newInstance("新闻","news"))
        fragments.add(MineFragment.newInstance("个人","mine"))
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
       return fragments.get(position)
    }



}