package com.example.hackathon2021.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon2021.data.res.ResSearchSchool
import com.example.hackathon2021.util.RetrofitConfig
import kotlinx.coroutines.launch

class SignUpSchoolViewModel : ViewModel() {
    private val accountRetrofit = RetrofitConfig.accountRetrofit
    val searchSchoolRes = MutableLiveData<List<ResSearchSchool>>()
    fun searchSchool(query: String) {
        viewModelScope.launch {
            accountRetrofit.searchSchool(query).let {
                if (it.isSuccessful){
                    Log.d("searchSchool",it.body()?.data!!.toString())
                    searchSchoolRes.postValue(it.body()?.data!!)
                }
                else{
                    Log.d("searchSchool",it.errorBody().toString())
                }
            }
        }
    }
}