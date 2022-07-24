package com.nix.summer.finaly.ui.adapters

import com.nix.summer.finaly.core.entity.*
import com.nix.summer.finaly.core.interactors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private var buyInteractor: BuyCoffeeInteractor,
    private var takeInteractor: TakeMoneyInteractor,
    private var fillInteractor: FillResourcesInteractor,
    private val exchangeCurrencyInteractor: ExchangeCurrencyInteractor,
    private val loadPaymentInteractor: LoadPaymentInteractor
) : Contract.Presenter, CoroutineScope {

    private var view: Contract.View? = null

    private val job = Job()
    override val coroutineContext: CoroutineContext = job + Dispatchers.Default

    override fun attach(view: Contract.View) {
        this.view = view

    }

    override fun detach() {
        this.view = null
    }


    fun getCommand(command: String, ingredients: Ingredients, type: Coffee = Coffee.ESPRESSO) {
        when (command) {
            "make" -> makeCoffee(type)
            "take" -> takeMoney()
            "fill" -> fillRecourses(ingredients)
        }
    }

    private fun makeCoffee(type: Coffee) {
        view?.showInfo(buyInteractor(type))
    }

    private fun takeMoney() {
        view?.showInfo(takeInteractor())
    }

    private fun fillRecourses(ingredients: Ingredients) {
        view?.showInfo(fillInteractor(ingredients))
    }

    fun exchangePayment(currency: String) {
        var response: Response
        launch {
            response = exchangeCurrencyInteractor(Payment(null, Coffee.ESPRESSO.money, currency))
            view?.showEspressoCost(response)
            response = exchangeCurrencyInteractor(Payment(null, Coffee.LATTE.money, currency))
            view?.showLatteCost(response)
            response = exchangeCurrencyInteractor(Payment(null, Coffee.CAPPUCCINO.money, currency))
            view?.showCappuccinoCost(response)
        }
    }

    fun addPayment() {
        launch {
            val response = loadPaymentInteractor()
            withContext(Dispatchers.Main) {
                view?.showPayment(response)
            }
        }
    }

}