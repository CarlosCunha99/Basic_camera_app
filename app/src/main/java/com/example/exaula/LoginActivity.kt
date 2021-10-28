package com.example.exaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import  androidx.lifecycle.ViewModelProvider

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel =
            ViewModelProvider(this).get(LoginViewModel::class.java)

        configurar()
    }

    private fun configurar(){
        findViewById<Button>(R.id.buttonLogin).setOnClickListener { autenticar() }
    }
    private fun autenticar(){
        if(validarDadosAutenticar()) showMainActivity()
        else showLoginError()
    }
    private fun validarDadosAutenticar(): Boolean {

        val user: String = findViewById<EditText>(R.id.login_user).text.toString()
        val pass: String = findViewById<EditText>(R.id.login_password).text.toString()

        return loginViewModel.validarDadosAutenticar(user, pass)
    }
    private fun showMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showLoginError(){
        findViewById<TextView>(R.id.login_error).visibility = View.VISIBLE
    }

}