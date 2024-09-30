package com.example.mvvmretrofit.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvvmretrofit.model.DemoListItem

@Dao
interface DemoDao {
    @Insert
    suspend fun addDemoData(demolist:List<DemoListItem>)

    @Query("SELECT * FROM demodata")
    suspend fun getDemoData():List<DemoListItem>
}