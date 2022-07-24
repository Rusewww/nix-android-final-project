package com.nix.summer.finaly.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DatabasePayment::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentDao(): PaymentDao
}