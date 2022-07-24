package com.nix.summer.finaly.ui.adapters

import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Payment
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.core.interactors.BuyCoffeeInteractor
import com.nix.summer.finaly.core.interactors.ExchangeCurrencyInteractor
import com.nix.summer.finaly.core.interactors.FillResourcesInteractor
import com.nix.summer.finaly.core.interactors.TakeMoneyInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private var buyInteractor: BuyCoffeeInteractor,
    private var takeInteractor: TakeMoneyInteractor,
    private var fillInteractor: FillResourcesInteractor,
    private val exchangeCurrencyInteractor: ExchangeCurrencyInteractor,
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
        launch {
            var response = exchangeCurrencyInteractor(Payment(Coffee.ESPRESSO.money, currency))
            view?.showEspressoCost(response)
            response = exchangeCurrencyInteractor(Payment(Coffee.CAPPUCCINO.money, currency))
            view?.showCappuccinoCost(response)
            response = exchangeCurrencyInteractor(Payment(Coffee.LATTE.money, currency))
            view?.showLatteCost(response)
        }
    }


}