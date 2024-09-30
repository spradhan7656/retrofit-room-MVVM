package com.example.mvvmretrofit.api

import com.example.mvvmretrofit.model.DemoList
import retrofit2.Response
import retrofit2.http.GET

interface DemoServices {
    @GET("/photos")
    suspend fun getDemolist() : Response<DemoList>
}