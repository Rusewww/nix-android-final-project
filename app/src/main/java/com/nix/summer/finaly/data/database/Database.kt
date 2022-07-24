package com.nix.summer.finaly.data.database

import android.content.Context
import androidx.room.Room

object Database {

    private const val DB_NAME = "coffeeDB"

    fun provideDao(context: Context): PaymentDao = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DB_NAME
    )
        .build()
        .paymentDao()
}