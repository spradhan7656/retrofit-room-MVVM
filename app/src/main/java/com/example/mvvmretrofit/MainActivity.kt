package com.example.mvvmretrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.api.DemoServices
import com.example.mvvmretrofit.api.RetrofitHelper
import com.example.mvvmretrofit.repository.DemoRepository
import com.example.mvvmretrofit.repository.Response
import com.example.mvvmretrofit.viewmodels.MainViewModel
import com.example.mvvmretrofit.viewmodels.MainViewModelFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository=(application as DemoApplication).demoRepository
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.demos.observe(this, Observer {

            /**
             * in this check the response state
             */
            when(it){
                is Response.Loading->{
                    Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_LONG).show()
                }
                is Response.Success->{
                    it.data?.let{
                        it.forEach {
                            Toast.makeText(this@MainActivity, it.url, Toast.LENGTH_LONG).show()
                        }
                    }

                }
                is Response.Error->{
                    // if the response from the server
                    // it.errormessage?.let { it.errormessage}
                    Toast.makeText(this@MainActivity, "error showing", Toast.LENGTH_LONG).show()
                }

            }
//           it.forEach{
//              Log.d("data",it.id.toString())
//           }
        })
    }
}