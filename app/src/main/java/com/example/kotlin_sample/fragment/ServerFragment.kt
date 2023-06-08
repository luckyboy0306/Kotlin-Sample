package com.example.kotlin_sample.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import com.example.kotlin_sample.R
import com.example.kotlin_sample.bean.ProjectTreeBean
import com.example.kotlin_sample.databinding.FragmentServerBinding
import com.example.kotlin_sample.fragment.model.ServerFragmentVM
import com.example.kotlin_sample.util.LiveDataBus

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ServerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ServerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val TAG = this::class.java.simpleName

    private lateinit var contentView : FragmentServerBinding

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
        contentView = bind<FragmentServerBinding>(inflater.inflate(R.layout.fragment_server, container, false))!!
        contentView.serverVM = ServerFragmentVM()
        LiveDataBus.get().with("projectTree",List::class.java).observe(viewLifecycleOwner) {
            contentView.serverFTable.removeAllTabs()
            for (i in it.indices) {
                val tab = contentView.serverFTable.newTab()
                tab.text = (it[i] as ProjectTreeBean).name
                contentView.serverFTable.addTab(tab)
            }

        }

        return contentView.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ServerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ServerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}