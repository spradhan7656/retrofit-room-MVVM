package com.example.mvvmretrofit.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmretrofit.api.DemoServices
import com.example.mvvmretrofit.db.DemoDatabase
import com.example.mvvmretrofit.model.DemoList
import com.example.mvvmretrofit.model.DemoListItem
import com.example.mvvmretrofit.model.PostList
import com.example.mvvmretrofit.utils.NetworkUtils

class DemoRepository(
    private val demoServices: DemoServices,
    private val demoDatabase: DemoDatabase,
    private val applicationContext: Context
) {
    private val demoLiveData=MutableLiveData<Response<DemoList>>()
    private val demoLivepost=MutableLiveData<Response<PostList>>()

    val postes: LiveData<Response<PostList>>
    get() = demoLivepost

    val demos:LiveData<Response<DemoList>>
    get() = demoLiveData

    suspend fun getDemoList(){


        /**
         * if the network is connected the data fetched from the api and strore the database
         *
         * if the network is not connected the data fetched from the database
         */
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                /**
                 * i check the response status form the server is loading or error and success
                 */
                demoLiveData.postValue(Response.Loading())
                val result =demoServices.getDemolist()
                if(result?.body() != null){
                    demoDatabase.demoDao().addDemoData(result.body()!!)
                    demoLiveData.postValue(Response.Success(result.body()))
                }
                else{
                    demoLiveData.postValue(Response.Error("error"))
                }
            }
            catch (e: Exception) {
                demoLiveData.postValue(Response.Error("error "))
            }
        }else{

            val demoData=demoDatabase.demoDao().getDemoData()
            var demolist=DemoList()
            demolist.addAll(demoData)
            demoLiveData.postValue(Response.Success(demolist))
            /**
             * if you want to try catch using if the database throws any kind of exception
             */
        }

    }
    suspend fun getPostList(){
        if(NetworkUtils.isInternetAvailable(applicationContext)){
            try {
                demoLivepost.postValue(Response.Loading())
                val res =demoServices.getPostsList()
                if(res?.body() != null){
                    demoLivepost.postValue(Response.Success(res.body()))
                }
                else{
                    demoLivepost.postValue(Response.Error("error"))
                }
            }
            catch (e: Exception) {
                demoLivepost.postValue(Response.Error("error "))
            }
        }else{

        }
    }
    suspend fun getDemosBackground(){
        val result=demoServices.getDemolist()
        if(result?.body() != null){
            demoDatabase.demoDao().addDemoData(result.body()!!)
        }
    }
}


