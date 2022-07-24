package com.nix.summer.finaly.data.repository

import com.nix.summer.finaly.core.entity.Payment
import com.nix.summer.finaly.data.database.PaymentDao
import com.nix.summer.finaly.data.mappers.DatabasePaymentToPaymentMapper
import com.nix.summer.finaly.data.network.ExchangeServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.nix.summer.finaly.data.mappers.NetworkPaymentToPaymentMapper
import com.nix.summer.finaly.data.mappers.PaymentToDatabasePaymentMapper

class PaymentRepositoryImplementation(
    private val exchangeServiceApi: ExchangeServiceAPI,
    private val networkPaymentToPaymentMapper: NetworkPaymentToPaymentMapper,
    private val paymentDao: PaymentDao,
    private val databasePaymentToPaymentMapper: DatabasePaymentToPaymentMapper,
    private val paymentToDatabasePaymentMapper: PaymentToDatabasePaymentMapper
) : PaymentRepository {

    override suspend fun makeNetworkExchange(payment: Payment): Payment {
        val networkPayment = withContext(Dispatchers.IO) {
            exchangeServiceApi.exchangeCurrency(
                "USD/${payment.currency}/${payment.amount}"
            )
        }
        return networkPaymentToPaymentMapper.toDomain(networkPayment)
    }

    override fun savePayment(payment: Payment) {
        val databasePayment = paymentToDatabasePaymentMapper.toData(payment)
        paymentDao.add(databasePayment)
    }

    override fun loadPayment(): Payment? {
        val databasePayment = paymentDao.getPaymentById()
        return databasePayment?.let {
            databasePaymentToPaymentMapper.toDomain(it)
        }
    }
}