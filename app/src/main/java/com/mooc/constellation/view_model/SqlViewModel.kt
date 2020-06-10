package com.mooc.constellation.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mooc.constellation.bean.ConstellationBean
import com.mooc.constellation.repository.ConstellationDatabase
import com.mooc.constellation.repository.ConstellationRepository
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