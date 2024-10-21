package com.example.mvvmretrofit.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmretrofit.repository.DemoRepository

class AnotherViewModelFactory(private val repository:DemoRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnotherViewModel(repository) as T
    }
}