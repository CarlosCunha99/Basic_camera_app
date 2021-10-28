package com.example.exaula
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){
    fun validarDadosAutenticar(user: String, pass: String): Boolean {
        return user == pass
    }

}