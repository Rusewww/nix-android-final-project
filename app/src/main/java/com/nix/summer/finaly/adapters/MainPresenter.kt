package com.nix.summer.finaly.adapters

import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.model.CoffeeMachine

class MainPresenter(private val machine: CoffeeMachine) : Contract.Presenter {

    private var view: Contract.View? = null

    override fun attach(view: Contract.View) {
        this.view = view
    }

    override fun detach() {
        this.view = null
    }

    fun start() {
        view?.showInfo(machine.info())
    }

    fun getCommand(command: String, ingredients: Ingredients, type: Coffee = Coffee.ESPRESSO){
        when(command){
            "make" -> makeCoffee(type)
            "take" -> takeMoney()
            "fill" -> fillRecourses(ingredients)
        }
    }

    private fun makeCoffee(type: Coffee) {
        view?.showInfo(machine.buy(type))
    }

    private fun takeMoney() {
        view?.showInfo(machine.take())
    }

    private fun fillRecourses(ingredients: Ingredients) {
        view?.showInfo(machine.fill(ingredients))
    }

}