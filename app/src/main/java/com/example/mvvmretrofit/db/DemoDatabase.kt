package com.example.mvvmretrofit.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmretrofit.model.DemoListItem

@Database(entities = [DemoListItem::class], version = 1)
abstract class DemoDatabase:RoomDatabase() {

    abstract fun demoDao():DemoDao
    companion object{
        @Volatile
        private var INSTANCE:DemoDatabase?=null
        fun getDatabase(context: Context):DemoDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,
                        DemoDatabase::class.java,
                        "DemoDatabase")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}