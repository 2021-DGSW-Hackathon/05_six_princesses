package com.example.hackathon2021.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon2021.dao.PostRetrofit
import com.example.hackathon2021.data.Comment
import com.example.hackathon2021.data.req.ReqPostComment
import com.example.hackathon2021.data.res.Res
import com.example.hackathon2021.util.RetrofitConfig
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    private val postRetrofit: PostRetrofit = RetrofitConfig.postRetrofit
    val resGetComments = MutableLiveData<List<Comment>>()
    val resPostComment = MutableLiveData<Res<Any>>()
    val resDeleteComment = MutableLiveData<Res<Any>>()
    fun getComments(boardId: Int) {
        viewModelScope.launch {
            postRetrofit.getComments(boardId).let {
                if (it.isSuccessful) {
                    Log.d("detail", it.body()?.data.toString())
                    resGetComments.postValue(it.body()?.data!!)
                } else {
                    Log.d("detail", it.errorBody().toString())
                }
            }

        }
    }

    fun postComment(reqPostComment: ReqPostComment) {
        viewModelScope.launch {
            postRetrofit.postComment(reqPostComment).let {
                if (it.isSuccessful) {
                    resPostComment.postValue(it.body())
                } else {
                    Log.d("detail", it.errorBody().toString())
                }
            }
        }
    }

    fun deleteComment(commentId: Int) {
        viewModelScope.launch {
            postRetrofit.deleteComment(commentId).let {
                if (it.isSuccessful) {
                    Log.d("detail", it.body().toString())

                    resDeleteComment.postValue(it.body())
                } else {
                    Log.d("detail", it.errorBody().toString())
                }
            }
        }
    }
}