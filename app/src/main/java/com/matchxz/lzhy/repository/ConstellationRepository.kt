package com.matchxz.lzhy.repository

import androidx.lifecycle.LiveData
import com.matchxz.lzhy.bean.ConstellationBean
import com.matchxz.lzhy.dao.ConstellationDao

class ConstellationRepository(private val constellationDao: ConstellationDao) {
    suspend fun insert(constellation: ConstellationBean) {
        constellationDao.insert(constellation)
    }

    fun queryForMen(menName: String, womenName: String): LiveData<ConstellationBean> =
        constellationDao.queryForMen(menName, womenName)

    fun queryForAll(): LiveData<List<ConstellationBean>> =
        constellationDao.queryForAll()
}