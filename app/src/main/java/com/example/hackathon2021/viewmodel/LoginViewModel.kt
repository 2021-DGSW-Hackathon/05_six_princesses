package com.example.hackathon2021.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackathon2021.data.Account
import com.example.hackathon2021.data.res.ResLogin
import com.example.hackathon2021.data.res.Res
import com.example.hackathon2021.util.RetrofitConfig
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val accountRetrofit = RetrofitConfig.accountRetrofit

    var loginRes = MutableLiveData<Res<ResLogin>?>()

    fun login(account: Account) {
        viewModelScope.launch {
            accountRetrofit.login(account).let {
                if (it.isSuccessful) {
                    loginRes.postValue(it.body())
                } else {
                    Log.d("login",it.errorBody().toString())
                }
            }
        }
    }
}