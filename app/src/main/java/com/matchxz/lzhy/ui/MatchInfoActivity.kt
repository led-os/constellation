package com.matchxz.lzhy.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matchxz.lzhy.Constant
import com.matchxz.lzhy.R
import com.matchxz.lzhy.view_model.SqlViewModel
import kotlinx.android.synthetic.main.activity_info.*

class MatchInfoActivity : AppCompatActivity() {
    private lateinit var sqlViewModel: SqlViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val men = intent.getStringExtra("men")
        val women = intent.getStringExtra("women")
        sqlViewModel = ViewModelProvider(this).get(SqlViewModel::class.java)
        sqlViewModel.queryForMen(men!!, women!!).observe(this, Observer { constellation ->
            if (constellation == null) {
                Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show()
                this.finish()
                return@Observer
            }
            icon_men.apply {
                Constant.datas.map {
                    if (constellation.men == it["name"]) {
                        this.setImageResource(it["icon"] as Int)
                    }
                }
            }

            icon_women.apply {
                Constant.datas.map {
                    if (constellation.women == it["name"]) {
                        this.setImageResource(it["icon"] as Int)
                    }
                }
            }

            match_title.text =
                getString(R.string.match_title, constellation.men, constellation.women)
            match_vs.text = getString(R.string.match_vs, constellation.men, constellation.women)
            match_pingfen.text =
                getString(R.string.match_pingfen, constellation.zhishu, constellation.jieguo)

            match_bizhong.text =
                getString(R.string.match_bizhong, constellation.bizhong)
            lianai.text = constellation.lianai
            zhuyi.text = constellation.zhuyi
            xiangyue.text = getString(R.string.match_xiangyue, constellation.xiangyue)
            tcdj.text = getString(R.string.match_tcdj, constellation.tcdj)
        })
    }
}