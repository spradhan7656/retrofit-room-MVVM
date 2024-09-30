package com.example.mvvmretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofit.model.DemoList
import com.example.mvvmretrofit.repository.DemoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DemoRepository) :ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDemoList()
        }
    }
    val demos:LiveData<DemoList>
        get()=repository.demos
}