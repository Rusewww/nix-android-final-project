package com.nix.summer.finaly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)
    }

    fun startMainActivity(view : View){
        val buyActivity = Intent(this, MainActivity::class.java)
        startActivity(buyActivity)
    }
}