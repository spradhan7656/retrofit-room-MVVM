package com.example.mvvmretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.repository.Response
import com.example.mvvmretrofit.viewmodels.AnotherViewModel
import com.example.mvvmretrofit.viewmodels.AnotherViewModelFactory

class Another : AppCompatActivity() {
    lateinit var anotherViewModel: AnotherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        val repository=(application as DemoApplication).demoRepository

        anotherViewModel= ViewModelProvider(this, AnotherViewModelFactory(repository)).get(AnotherViewModel::class.java)
        anotherViewModel.posts.observe(this, Observer{
            when(it){
                is Response.Success ->{
                    it.data?.let{
                        it.forEach {
                            Toast.makeText(this@Another, it.body, Toast.LENGTH_LONG).show()
                        }
                    }
                }
                is Response.Error ->{
                    //handle error
                }
                is Response.Loading ->{
                  Toast.makeText(this@Another,"Loading...", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}