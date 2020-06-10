package com.mooc.constellation.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constellation_table")
data class ConstellationBean(
    @PrimaryKey(autoGenerate = true) var Id: Long?,
    val bizhong: String,
    val jieguo: String,
    val lianai: String,
    val men: String,
    val tcdj: String,
    val women: String,
    val xiangyue: String,
    val zhishu: String,
    val zhuyi: String
)
