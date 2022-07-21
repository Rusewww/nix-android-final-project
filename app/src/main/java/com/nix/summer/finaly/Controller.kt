package com.nix.summer.finaly

class Controller(private val machine: CoffeeMachine) {

    private lateinit var view: MainActivity

    fun start(){
        view.showInfo(machine.info())
    }

    fun attachView(_view: MainActivity) {
        view = _view
    }

    fun makeCoffee(type: Coffee) {
        view.showInfo(machine.buy(type))
    }

    fun takeMoney(){
        view.showInfo(machine.take());
    }

    fun fillRecourses(ingredients: Ingredients) {
        view.showInfo(machine.fill(ingredients))
    }

}