package com.example.hackathon2021.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon2021.data.req.ReqPost
import com.example.hackathon2021.data.res.Res
import com.example.hackathon2021.util.RetrofitConfig
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    private val postRetrofit = RetrofitConfig.postRetrofit
    val postRes = MutableLiveData<Res<Any>>()

    fun post(reqPost: ReqPost) {
        viewModelScope.launch {
            postRetrofit.post(reqPost).let {
                if (it.isSuccessful) {
                    postRes.postValue(it.body())
                } else {
                    Log.d("post", it.errorBody().toString())
                }
            }
        }
    }
}