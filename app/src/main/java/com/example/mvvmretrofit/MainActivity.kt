package com.example.mvvmretrofit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
    lateinit var btn:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById(R.id.button)
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
                    /**
                     * Why use .?let
                     * Safe Call with ?.let
                     * ?.let is used when you want to execute a block of code only if the object (it.data) is not null.
                     * In your code, it.data might be null, and using ?.let ensures that the code inside the let block is only executed when it.data is not null. If it.data is null, the block is skipped.
                     */
                    it.data?.let{
                        it.forEach {
//                            Toast.makeText(this@MainActivity, it.url, Toast.LENGTH_LONG).show()
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
        btn.setOnClickListener(View.OnClickListener {
            val intent= Intent(this@MainActivity,Another::class.java)
            startActivity(intent)
        })
    }
}