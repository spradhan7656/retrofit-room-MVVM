package com.example.mvvmretrofit.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretrofit.api.DemoServices
import com.example.mvvmretrofit.db.DemoDatabase
import com.example.mvvmretrofit.model.DemoList
import com.example.mvvmretrofit.model.DemoListItem
import com.example.mvvmretrofit.utils.NetworkUtils

class DemoRepository(
    private val demoServices: DemoServices,
    private val demoDatabase: DemoDatabase,
    private val applicationContext: Context
) {
    private val demoLiveData=MutableLiveData<DemoList>()


    val demos:LiveData<DemoList>
    get() = demoLiveData

    suspend fun getDemoList(){


        /**
         * if the network is connected the data fetched from the api and strore the database
         *
         * if the network is not connected the data fetched from the database
         */
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            Log.d("check", "true")
            val result =demoServices.getDemolist()
            if(result?.body() != null){
                demoDatabase.demoDao().addDemoData(result.body()!!)
                demoLiveData.postValue(result.body())
            }
        }else{
            Log.d("check ","false")
            val demoData=demoDatabase.demoDao().getDemoData()
            var demolist=DemoList()
            demolist.addAll(demoData)
            demoLiveData.postValue(demolist)
        }

    }
}