package com.example.hackathon2021.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon2021.data.Board
import com.example.hackathon2021.data.res.Res
import com.example.hackathon2021.util.RetrofitConfig
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val postRetrofit = RetrofitConfig.postRetrofit
    val resGetBoards = MutableLiveData<List<Board>>()
    val resSearchBoards = MutableLiveData<List<Board>>()
    val resDeleteBoard = MutableLiveData<Res<Any>>()
    fun getBoards() {
        viewModelScope.launch {
            postRetrofit.get().let {
                if (it.isSuccessful) {
                    Log.d("main", it.body()?.data.toString())
                    resGetBoards.postValue(it.body()?.data!!)
                } else {
                    Log.d("main", it.errorBody().toString())
                }
            }
        }
    }

    fun searchBoards(value:String){
        viewModelScope.launch {
            postRetrofit.searchPost(value).let {
                if (it.isSuccessful){
                    resSearchBoards.postValue(it.body()?.data!!)
                }else{
                    Log.d("main", it.errorBody().toString())
                }
            }
        }
    }

    fun deleteBoard(boardId:Int){
        viewModelScope.launch {
            postRetrofit.deleteBoard(boardId).let {
                if (it.isSuccessful){
                    resDeleteBoard.postValue(it.body())
                }else{
                    Log.d("main", it.errorBody().toString())
                }
            }
        }
    }

}