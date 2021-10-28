package com.example.exaula
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){
    private val loginResult = MutableLiveData<Boolean>()
        val loginResultLiveData = loginResult

    fun validarDadosAutenticar(user: String, pass: String){

        loginResultLiveData.postValue(user == pass)
    }

}