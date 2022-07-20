package com.nix.summer.finaly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take)
    }

    fun startMainActivity(view : View){
        val buyActivity = Intent(this, MainActivity::class.java)
        startActivity(buyActivity)
    }
}