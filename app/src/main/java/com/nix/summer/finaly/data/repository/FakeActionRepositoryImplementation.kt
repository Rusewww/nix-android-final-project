package com.nix.summer.finaly.data.repository

import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Response

class FakeActionRepositoryImplementation : ActionRepository {
    object CoffeeMachine {
        var water = 0
        var milk = 0
        var coffee = 0
        var disposableCups = 0
        var money = 0
    }

    private fun check(coffeeType: String): Boolean {
        if (CoffeeMachine.water - Coffee.valueOf(coffeeType).water >= 0
            && CoffeeMachine.milk - Coffee.valueOf(coffeeType).milk >= 0
            && CoffeeMachine.coffee - Coffee.valueOf(coffeeType).coffeeBeans >= 0
            && CoffeeMachine.disposableCups > 0
        ) return true
        if (CoffeeMachine.water - Coffee.valueOf(coffeeType).water < 0) println("Sorry, not enough water!")
        if (CoffeeMachine.milk - Coffee.valueOf(coffeeType).milk < 0) println("Sorry, not enough milk!")
        if (CoffeeMachine.coffee - Coffee.valueOf(coffeeType).coffeeBeans < 0) println("Sorry, not enough coffee beans!")
        if (CoffeeMachine.disposableCups < 0) println("Sorry,  not enough disposable cups!")
        return false
    }

    override fun buy(coffeeType: Coffee): Response {
        val check = this.check(coffeeType.name)
        return if (check) {
            CoffeeMachine.water -= coffeeType.water
            CoffeeMachine.milk -= coffeeType.milk
            CoffeeMachine.coffee -= coffeeType.coffeeBeans
            CoffeeMachine.money += coffeeType.money
            CoffeeMachine.disposableCups -= 1
            Response(
                "I have enough resources, making you a coffee!",
                Ingredients(
                    CoffeeMachine.water,
                    CoffeeMachine.milk,
                    CoffeeMachine.coffee,
                    CoffeeMachine.disposableCups
                )
            )
        } else {
            Response(
                "Not enough ingredients, see console for details",
                Ingredients(
                    CoffeeMachine.water,
                    CoffeeMachine.milk,
                    CoffeeMachine.coffee,
                    CoffeeMachine.disposableCups
                )
            )
        }
    }

    override fun fill(ingredients: Ingredients): Response {
        CoffeeMachine.water += ingredients.water
        CoffeeMachine.milk += ingredients.milk
        CoffeeMachine.coffee += ingredients.coffee
        CoffeeMachine.disposableCups += ingredients.disposableCups
        return (Response(
            "Filled",
            Ingredients(
                CoffeeMachine.water,
                CoffeeMachine.milk,
                CoffeeMachine.coffee,
                CoffeeMachine.disposableCups
            )
        ))
    }

    override fun take(): Response {
        val out = CoffeeMachine.money
        CoffeeMachine.money = 0
        return Response(
            "I gave you $out UAH!",
            Ingredients(CoffeeMachine.water, CoffeeMachine.milk, CoffeeMachine.coffee, CoffeeMachine.disposableCups)
        )
    }
}