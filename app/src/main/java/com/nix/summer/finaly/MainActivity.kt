package com.nix.summer.finaly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var milkView: TextView
    private lateinit var waterView: TextView
    private lateinit var coffeeView: TextView
    private lateinit var cupView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showInfo()
    }

    fun showInfo(){
        coffeeView = findViewById(R.id.coffee_text)
        coffeeView.text = "0 g"
        milkView = findViewById(R.id.milk_text)
        milkView.text = "0 ml"
        waterView = findViewById(R.id.water_text)
        waterView.text = "0 ml"
        cupView = findViewById(R.id.cup_text)
        cupView.text = "0"
    }
}