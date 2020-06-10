package com.matchxz.lzhy.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.matchxz.lzhy.bean.ConstellationBean
import com.matchxz.lzhy.repository.ConstellationDatabase
import com.matchxz.lzhy.repository.ConstellationRepository
import kotlinx.coroutines.launch

class SqlViewModel(application: Application) : AndroidViewModel(application) {

    private val constellationRepository: ConstellationRepository

    init {
        val constellationDao = ConstellationDatabase.getInstance(application).constellationDao()
        constellationRepository = ConstellationRepository(constellationDao)

    }

    fun insert(constellationBean: ConstellationBean) {
        viewModelScope.launch {
            constellationRepository.insert(constellationBean)
        }
    }

    fun queryForMen(menName: String, womenName: String): LiveData<ConstellationBean> {
        return constellationRepository.queryForMen(menName, womenName)

    }

    fun queryForAll(): LiveData<List<ConstellationBean>> {
        return constellationRepository.queryForAll()
    }


}