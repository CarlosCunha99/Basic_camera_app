package com.example.exaula

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

private const val REQUEST_CODE_CAMARA = 2110

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configure()
    }

    private fun configure(){
        findViewById<Button>(R.id.camera_btn).setOnClickListener { openCamera(it) }
        findViewById<Button>(R.id.edit_btn).setOnClickListener { showEdit() }
        findViewById<TextView>(R.id.main_nome).text = intent.getStringExtra("NOME")
    }

    // Abre a cam e coloca a foto como bipmap na image view
    private fun openCamera(view:View){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_CODE_CAMARA)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:Intent?){
        if(requestCode == REQUEST_CODE_CAMARA && resultCode == RESULT_OK){
            val imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.main_img).setImageBitmap(imageBitmap)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    //Edit
    private fun showEdit(){
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
        finish()
    }




}