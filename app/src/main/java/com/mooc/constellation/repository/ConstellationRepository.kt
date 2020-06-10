package com.mooc.constellation.repository

import androidx.lifecycle.LiveData
import com.mooc.constellation.bean.ConstellationBean
import com.mooc.constellation.dao.ConstellationDao

class ConstellationRepository(private val constellationDao: ConstellationDao) {
    suspend fun insert(constellation: ConstellationBean) {
        constellationDao.insert(constellation)
    }

    fun queryForMen(menName: String, womenName: String): LiveData<ConstellationBean> =
        constellationDao.queryForMen(menName, womenName)

    fun queryForAll(): LiveData<List<ConstellationBean>> =
        constellationDao.queryForAll()
}