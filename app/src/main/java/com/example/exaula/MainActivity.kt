package com.example.exaula

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

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
        Log.i("ExAula", "onActivityResult( $requestCode)")

        /*with(resultCode) {
            RESULT_OK?{
                if (requestCode == REQUEST_CODE_CAMARA) {
                    val imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
                    findViewById<ImageView>(R.id.main_img).setImageBitmap(imageBitmap)
                }else()
            }
        }*/
        if(requestCode == REQUEST_CODE_CAMARA && resultCode == RESULT_OK){
            val imageBitmap: Bitmap = data?.extras?.get("data") as Bitmap
            findViewById<ImageView>(R.id.main_img).setImageBitmap(imageBitmap)
        }else{
            val nome: String = data?.extras?.get("NOME") as String
            findViewById<TextView>(R.id.main_nome).text = nome
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    //Edit
    private fun showEdit(){
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent,101)
    }




}