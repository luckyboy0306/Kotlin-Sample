package com.example.kotlin_sample.page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil.*
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import com.example.kotlin_sample.R
import com.example.kotlin_sample.adapter.MainFragmentAdapter
import com.example.kotlin_sample.databinding.ActivityMainBinding
import com.example.kotlin_sample.modle.MainViewModle
import com.example.kotlin_sample.util.LiveDataBus
import com.example.kotlin_sample.util.StatusBarKt

class MainActivity : AppCompatActivity() {
    val TAG = this::class.java.simpleName

    lateinit var contentView: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StatusBarKt.fitSystemBar(this)
        StatusBarKt.lightStatusBar(this, true)
        contentView = setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        contentView.mainViewModel = MainViewModle()
        contentView.mainVp.isUserInputEnabled = false
        contentView.mainVp.adapter = MainFragmentAdapter(this)
        contentView.mainVp.registerOnPageChangeCallback(object : androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                contentView.mainBnv.menu.getItem(position).isChecked = true
            }
        })
        contentView.mainBnv.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> contentView.mainVp.setCurrentItem(0, false)
                R.id.menu_service -> contentView.mainVp.setCurrentItem(1, false)
                R.id.menu_news -> contentView.mainVp.setCurrentItem(2, false)
                R.id.menu_mine -> contentView.mainVp.setCurrentItem(3, false)
            }
            true
        }
        LiveDataBus.get().with("openDrawer").observe(this, androidx.lifecycle.Observer {
            contentView.mainDl.openDrawer(GravityCompat.START)
        })
        contentView.mainDl.addDrawerListener(object : DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.i(TAG, "onDrawerSlide: ")
            }

            override fun onDrawerOpened(drawerView: View) {
                Log.i(TAG, "onDrawerOpened: ")
            }

            override fun onDrawerClosed(drawerView: View) {
                Log.i(TAG, "onDrawerClosed: ")
            }

            override fun onDrawerStateChanged(newState: Int) {
                Log.i(TAG, "onDrawerStateChanged: ")
            }

        })
    }


}