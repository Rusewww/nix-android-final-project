package com.nix.summer.finaly.core.interactors

import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.data.repository.PaymentRepository

class LoadPaymentInteractor(private val repository: PaymentRepository) {
    operator fun invoke(): Response {
        val payment = repository.loadPayment()
        return Response(
            responseString = "Action saved in DB: ${payment?.amount ?: "None"} " +
                    (payment?.currency ?: "Unknown"), Ingredients()
        )
    }
}