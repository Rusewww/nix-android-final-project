package com.nix.summer.finaly.data.repository

import com.nix.summer.finaly.core.entity.Payment

interface PaymentRepository {
    suspend fun makeNetworkExchange(payment: Payment): Payment
    fun savePayment(payment: Payment)
    fun loadPayment(): Payment?
}