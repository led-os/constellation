package com.mooc.constellation.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mooc.constellation.bean.ConstellationBean

@Dao
interface ConstellationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: ConstellationBean)

    /**
     * 如果在Room中方法返回值得类型定义为 LiveData 时，那么，该方法则默认是 异步 的
     */
    @Query("SELECT * from constellation_table where men = :name and women = :womenName")
    fun queryForMen(name: String, womenName: String): LiveData<ConstellationBean>

    /**
     * 如果在Room中方法返回值得类型定义为 LiveData 时，那么，该方法则默认是 异步 的
     */
    @Query("SELECT * from constellation_table")
    fun queryForAll(): LiveData<List<ConstellationBean>>
}  