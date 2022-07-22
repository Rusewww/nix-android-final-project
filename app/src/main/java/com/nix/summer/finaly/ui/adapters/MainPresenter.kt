package com.nix.summer.finaly.ui.adapters

import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.interactors.BuyCoffeeInteractor
import com.nix.summer.finaly.core.interactors.FillResourcesInteractor
import com.nix.summer.finaly.core.interactors.TakeMoneyInteractor

class MainPresenter(
    private var buyInteractor: BuyCoffeeInteractor,
    private var takeInteractor: TakeMoneyInteractor,
    private var fillInteractor: FillResourcesInteractor
) : Contract.Presenter {

    private var view: Contract.View? = null

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

}