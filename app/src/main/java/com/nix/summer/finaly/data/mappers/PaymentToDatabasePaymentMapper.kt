package com.nix.summer.finaly.data.mappers

import com.nix.summer.finaly.core.entity.Payment
import com.nix.summer.finaly.data.database.DatabasePayment

class PaymentToDatabasePaymentMapper {

    fun toData(payment: Payment): DatabasePayment = with(payment) {
        DatabasePayment(
            currency = currency,
            amount = amount
        )
    }
}