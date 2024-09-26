package com.developer.edra.buildyourfirtsapps_1

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity_Module_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_module1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val testButton: Button = findViewById(R.id.btn_test)

        testButton.text = "Nuevo Texto para el boton"

        testButton.setOnClickListener {
           // Toast.makeText(this, "CLICK AL BOTON", Toast.LENGTH_SHORT).show()
            changesTextRoll()
        }

    }

    //Funcion para cambiar el numero del textview en la vista
    private fun changesTextRoll() {
        val randomNumber = Random.nextInt(6) + 1
        val resultTextTest: TextView = findViewById(R.id.text_test)
        resultTextTest.text = randomNumber.toString()
    }

}