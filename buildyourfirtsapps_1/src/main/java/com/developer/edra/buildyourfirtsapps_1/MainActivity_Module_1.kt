package com.developer.edra.buildyourfirtsapps_1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity_Module_1 : AppCompatActivity() {

    lateinit var diceImage: ImageView

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

        testButton.text = getString(R.string.nuevo_texto_para_el_boton)

        testButton.setOnClickListener {
            // Toast.makeText(this, "CLICK AL BOTON", Toast.LENGTH_SHORT).show()
            changesTextRoll()
        }


        diceImage = findViewById(R.id.dice_image)
    }

    //Funcion para cambiar el numero del textview en la vista
    private fun changesTextRoll() {
        val randomNumber = Random.nextInt(6) + 1
//        val resultTextTest: TextView = findViewById(R.id.text_test)
        // resultTextTest.text = randomNumber.toString()

        //1.21 ejercicio y actualizado por el 1.23
        //  val diceImage: ImageView = findViewById(R.id.dice_image)
        val drawableResource = when (randomNumber) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
    }

}