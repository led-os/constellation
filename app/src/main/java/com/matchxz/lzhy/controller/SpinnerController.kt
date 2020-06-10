package com.matchxz.lzhy.controller

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatSpinner
import com.matchxz.lzhy.Constant.Companion.datas

class SpinnerController(private val icon: ImageView, private val spinner: AppCompatSpinner) {
    fun setUp() {
        val adapter = ArrayAdapter(
            spinner.context, android.R.layout.simple_spinner_item,
            datas.map { it["name"] }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                icon.apply {
                    datas.map {
                        if (parent.selectedItem == it["name"]) {
                            this.setImageResource(it["icon"] as Int)
                        }
                    }
                }
            }
        }
    }
}