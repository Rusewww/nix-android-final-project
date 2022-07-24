package com.nix.summer.finaly.core.interactors

import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Payment
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.data.repository.PaymentRepository

class ExchangeCurrencyInteractor(private val repository: PaymentRepository) {

    suspend operator fun invoke(payment: Payment): Response {
        val exchangedPayment = if (payment.currency != "USD") {
            repository.makeNetworkExchange(payment)
        } else {
            payment
        }

        return with(exchangedPayment) {
            Response(
                responseString = "$amount $currency",
                Ingredients()
            )
        }
    }
}