package com.nix.summer.finaly.data.mappers

import com.nix.summer.finaly.core.entity.Payment
import com.nix.summer.finaly.data.database.DatabasePayment

class DatabasePaymentToPaymentMapper {

    fun toDomain(databasePayment: DatabasePayment): Payment = with(databasePayment) {
        Payment(
            currency = currency,
            amount = amount
        )
    }
}