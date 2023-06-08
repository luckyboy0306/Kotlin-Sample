package com.example.kotlin_sample.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_sample.R
import com.example.kotlin_sample.adapter.BannerAdapter
import com.example.kotlin_sample.adapter.MainFragmentRVAdapter
import com.example.kotlin_sample.bean.MainArticlBean
import com.example.kotlin_sample.bean.MainBannerBean
import com.example.kotlin_sample.bean.MainFragmentProjectItemBean
import com.example.kotlin_sample.bean.ProjectTreeBean
import com.example.kotlin_sample.databinding.FragmentMainBinding
import com.example.kotlin_sample.fragment.model.MainFragmentVM
import com.example.kotlin_sample.util.LiveDataBus
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() ,MainFragmentVM.MainFragmentVMCallBack{
    val TAG = this::class.java.simpleName
    var bannerDatas = ArrayList<MainBannerBean>()
    var projectTreeDatas = ArrayList<ProjectTreeBean>()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var contentView : FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflate = inflater.inflate(R.layout.fragment_main, container, false)
        contentView = bind<FragmentMainBinding>(inflate)
        contentView?.mainFragmentVM = MainFragmentVM()
        contentView?.mainEvent = this
        contentView?.mainFBanner?.addBannerLifecycleObserver(this)?.apply {
            indicator = com.youth.banner.indicator.CircleIndicator(context)
            setAdapter(BannerAdapter(bannerDatas))
        }

        LiveDataBus.get().with("banner",List::class.java).observe(viewLifecycleOwner) {
            bannerDatas = it as ArrayList<MainBannerBean>
            contentView?.mainFBanner?.setDatas(bannerDatas)
        }

        contentView?.mainFTable?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                contentView?.mainFragmentVM?.switchTab(tab?.position ?: 0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        contentView?.mainFTable?.addTab(contentView?.mainFTable?.newTab()!!.setText("首页"))
        contentView?.mainFTable?.addTab(contentView?.mainFTable?.newTab()!!.setText("文章"))
        contentView?.mainFTable?.addTab(contentView?.mainFTable?.newTab()!!.setText("广场"))
//        LiveDataBus.get().with("projectTree",List::class.java).observe(viewLifecycleOwner) {
//            projectTreeDatas = it as ArrayList<ProjectTreeBean>
//            contentView?.mainFTable?.removeAllTabs()
//            for (i in projectTreeDatas) {
//                contentView?.mainFTable?.addTab(contentView?.mainFTable?.newTab()!!.setText(i.name))
//            }
//        }
        contentView?.mainFRv?.layoutManager = LinearLayoutManager(context)
        contentView?.mainFRv?.adapter = MainFragmentRVAdapter()
        contentView?.mainFRv?.isFocusable = false;
        LiveDataBus.get().with("projectItem",MainArticlBean::class.java).observe(viewLifecycleOwner) {
            contentView?.mainFRv?.adapter?.let { it1 -> (it1 as MainFragmentRVAdapter).setData(it.datas) }
        }
        return contentView?.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun openDrawer() {
        LiveDataBus.get().with("openDrawer").value = true
    }
}