package com.mooc.constellation.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mooc.constellation.http.RetrofitFactory
import com.mooc.constellation.bean.ConstellationBean
import com.mooc.constellation.http.ApiService
import com.mooc.constellation.http.dataConvert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetViewModel : ViewModel() {
    var constellation = MutableLiveData<ConstellationBean>()
    fun getConstellation(men: String, women: String, key: String) {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    RetrofitFactory.instance.getService(ApiService::class.java)
                        .getConstellations(
                            men,
                            women,
                            key
                        ).dataConvert()
                }
                constellation.value = data
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("请求失败", "${e.message}")
            }
        }
    }
}