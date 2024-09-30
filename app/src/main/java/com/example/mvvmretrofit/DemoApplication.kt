package com.example.mvvmretrofit

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.impl.WorkManagerImpl
import com.example.mvvmretrofit.api.DemoServices
import com.example.mvvmretrofit.api.RetrofitHelper
import com.example.mvvmretrofit.db.DemoDatabase
import com.example.mvvmretrofit.repository.DemoRepository
import com.example.mvvmretrofit.workmanagers.DemoWorker
import java.util.concurrent.TimeUnit

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
        setupWorker()
    }

    /**
     * Initializes the function setupWorker this function will work checking the internet connectivity and scheduling the
     * time to call the function
     */

    private fun setupWorker() {
        val constraint=Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest=PeriodicWorkRequest.Builder(DemoWorker::class.java,1200,TimeUnit.SECONDS)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialize() {

        val demoServices= RetrofitHelper.getInstance().create(DemoServices::class.java)
        val database=DemoDatabase.getDatabase(applicationContext)
         demoRepository=DemoRepository(demoServices,database,applicationContext)
    }
}