package com.nix.summer.finaly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startBuyActivity(view : View){
        val buyActivity = Intent(this, BuyActivity::class.java)
        startActivity(buyActivity)
    }

    fun startFillActivity(view : View){
        val fillActivity = Intent(this, FillActivity::class.java)
        startActivity(fillActivity)
    }

    fun startTakeActivity(view : View){
        val takeActivity = Intent(this, TakeActivity::class.java)
        startActivity(takeActivity)
    }
}