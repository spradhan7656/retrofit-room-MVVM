package com.example.mvvmretrofit.workmanagers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mvvmretrofit.DemoApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DemoWorker (private val context: Context,params:WorkerParameters):Worker(context,params) {

    /**
     * in the demoworker class need to context and params and inherit from the worker
     * dowork is override function that coroutinescope is called suspended fun and run as background
     *
     * this will define what work will do
     */
    override fun doWork(): Result {
        Log.d("checkeing ","worker")
        val repository=(context as DemoApplication).demoRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getDemosBackground()
        }
        return Result.success()
    }
}