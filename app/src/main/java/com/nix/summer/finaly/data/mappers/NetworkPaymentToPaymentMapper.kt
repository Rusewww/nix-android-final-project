package com.nix.summer.finaly.data.mappers

import com.nix.summer.finaly.core.entity.Payment
import com.nix.summer.finaly.data.network.NetworkPayment

class NetworkPaymentToPaymentMapper {

    fun toDomain(networkPayment: NetworkPayment): Payment = with(networkPayment) {
        Payment(
            currency = targetCurrency,
            amount = conversionResult.toFloat()
        )
    }
}