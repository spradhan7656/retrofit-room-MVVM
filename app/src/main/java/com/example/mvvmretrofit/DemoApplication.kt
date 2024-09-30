package com.example.mvvmretrofit

import android.app.Application
import com.example.mvvmretrofit.api.DemoServices
import com.example.mvvmretrofit.api.RetrofitHelper
import com.example.mvvmretrofit.db.DemoDatabase
import com.example.mvvmretrofit.repository.DemoRepository

class DemoApplication:Application() {

    /**
     * The application class used for if once create the database instance i use 2nd time then
     * next time i will not create if create once
     *
     * Define mainfest file add the <application name="" the application class
     */
    lateinit var demoRepository: DemoRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {

        val demoServices= RetrofitHelper.getInstance().create(DemoServices::class.java)
        val database=DemoDatabase.getDatabase(applicationContext)
         demoRepository=DemoRepository(demoServices,database,applicationContext)
    }
}