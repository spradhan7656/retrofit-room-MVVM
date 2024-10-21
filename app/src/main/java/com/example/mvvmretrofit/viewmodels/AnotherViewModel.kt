package com.example.mvvmretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmretrofit.model.PostList
import com.example.mvvmretrofit.repository.DemoRepository
import com.example.mvvmretrofit.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnotherViewModel(private val repository:DemoRepository):ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPostList()
        }
    }
    val posts:LiveData<Response<PostList>>
    get()=repository.postes
}