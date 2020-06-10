package com.matchxz.lzhy.http

import com.matchxz.lzhy.bean.ConstellationBean
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("https://apis.juhe.cn/xzpd/query")
    suspend fun getConstellations(
        @Query("men") men: String,
        @Query("women") women: String,
        @Query("key") key: String
    ): BaseResp<ConstellationBean>
}