package com.example.mvvmretrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demodata")
data class DemoListItem(
    @PrimaryKey(autoGenerate = true)
    val demoid:Int ,
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)