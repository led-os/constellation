package com.mooc.constellation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.mooc.constellation.Constant.Companion.datas
import com.mooc.constellation.R
import com.mooc.constellation.controller.SpinnerController
import com.mooc.constellation.ui.MatchInfoActivity
import com.mooc.constellation.view_model.NetViewModel
import com.mooc.constellation.view_model.SqlViewModel
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.activity_info.icon_men
import kotlinx.android.synthetic.main.activity_info.icon_women
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var netViewModel: NetViewModel
    private lateinit var sqlViewModel: SqlViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*创建viewmodel*/
        netViewModel = ViewModelProvider(this).get(NetViewModel::class.java)
        sqlViewModel = ViewModelProvider(this).get(SqlViewModel::class.java)
        SpinnerController(icon_men, spinner_men).setUp()
        SpinnerController(icon_women, spinner_women).setUp()
        sqlViewModel.queryForAll().observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.e("lxh", "msg ====$it")
            }

        })

        btn_matches.setOnClickListener {
            val intent = Intent(context, MatchInfoActivity::class.java)
            intent.putExtra("men", spinner_men.selectedItem.toString())
            intent.putExtra("women", spinner_women.selectedItem.toString())
            startActivity(intent)
//            getConstellation()

        }

        /*数据发生变化时更新ui*/
        netViewModel.constellation.observe(viewLifecycleOwner, Observer {
//            tv_msg.text = Gson().toJson(it)
            Log.e("http", "insert---$it")
            sqlViewModel.insert(it)
        })

    }

    fun getConstellation() {
        var count = 0;
        datas.forEach { it1 ->
            datas.forEach { it2 ->
                /*请求数据*/
                count++
                Log.e("http", "count===$count")
                netViewModel.getConstellation(
                    it1["name"].toString(),
                    it2["name"].toString(),
                    (if (count < 80) {
                        "a08fe8aa7f3143a8d99eb1e3186867b1"
                    } else {
                        "8ee3a70398a696f8ebd9a35b24ed25d0"
                    })
                )
            }
        }

    }


}