package com.example.exaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import  androidx.lifecycle.ViewModelProvider
import java.util.concurrent.Executor
import androidx.biometric.*

private lateinit var executor : Executor
private lateinit var biometricPrompt: BiometricPrompt
private lateinit var promptInfo: BiometricPrompt.PromptInfo

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel =
            ViewModelProvider(this)[LoginViewModel::class.java]

        configurar()
    }
    private fun configurar(){
        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            validarDadosAutenticar()
        }
        findViewById<Button>(R.id.biometrics_Button).setOnClickListener { biometricPrompt.authenticate(promptInfo) }
        loginViewModel.loginResultLiveData.observe(this){ //loginResult ->
            if (it){
                showMainActivity()
            }else showLoginError()
        }


        // Configurar a autenticação por biometria
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object: BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Something Failed ..." + errorCode + " / " + errString,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext, "Login Succeeded", Toast.LENGTH_SHORT).show()
                    showMainActivity()
                }
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
        // Costumiza o texto da autenticação
        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Autentication")
            .setSubtitle("Log in ussing your biometric data")
            .setNegativeButtonText("Use Acount Password")
            .build()
    }
    private fun validarDadosAutenticar() {

        val user : String =(findViewById<EditText>(R.id.login_user).text.toString())
        val pass : String = findViewById<EditText>(R.id.login_password).text.toString()

        loginViewModel.validarDadosAutenticar(user,pass )
    }
    private fun showMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showLoginError(){
        Toast.makeText(applicationContext, "Please verify your credentials", Toast.LENGTH_SHORT).show()
    }


}