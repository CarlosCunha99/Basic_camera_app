package com.example.exaula

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        configure()
    }

    private fun configure(){
        findViewById<Button>(R.id.edit_voltar).setOnClickListener { showMainActivity() }
    }
    private fun showMainActivity(){
        val nome: String =
            findViewById<EditText>(R.id.editTextTextPersonName).text.toString()

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("NOME",nome)

        setResult(RESULT_OK, intent)
        //startActivity(intent)
        finish()
    }
}