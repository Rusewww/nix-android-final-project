package com.nix.summer.finaly

data class Response(var responseString: String, val ingredients: Ingredients)

data class Ingredients(
    var water: Int = 0,
    var milk: Int = 0,
    var coffee: Int = 0,
    var disposableCups: Int = 0
)

class CoffeeMachine(
    private var water: Int,
    private var milk: Int,
    private var coffee: Int,
    private var disposableCups: Int,
    private var money: Int
) {

    fun info(): Response {
        return Response(
            "", Ingredients(this.water, this.milk, this.coffee, this.disposableCups)
        )
    }

    private fun check(coffeeType: String): Boolean {
        if (this.water - Coffee.valueOf(coffeeType).water >= 0
            && this.milk - Coffee.valueOf(coffeeType).milk >= 0
            && this.coffee - Coffee.valueOf(coffeeType).coffeeBeans >= 0
            && this.disposableCups > 0
        ) return true
        if (this.water - Coffee.valueOf(coffeeType).water < 0) println("Sorry, not enough water!")
        if (this.milk - Coffee.valueOf(coffeeType).milk < 0) println("Sorry, not enough milk!")
        if (this.coffee - Coffee.valueOf(coffeeType).coffeeBeans < 0) println("Sorry, not enough coffee beans!")
        if (this.disposableCups < 0) println("Sorry,  not enough disposable cups!")
        return false
    }

    fun buy(coffeeType: Coffee): Response {
        val check = this.check(coffeeType.name)
        return if (check) {
            this.water -= coffeeType.water
            this.milk -= coffeeType.milk
            this.coffee -= coffeeType.coffeeBeans
            this.money += coffeeType.money
            this.disposableCups -= 1
            Response(
                "I have enough resources, making you a coffee!",
                Ingredients(this.water, this.milk, this.coffee, this.disposableCups)
            )
        } else {
            Response(
                "Not enough ingredients, see console for details",
                Ingredients(this.water, this.milk, this.coffee, this.disposableCups)
            )
        }
    }

    fun fill(ingredients: Ingredients): Response {
        this.water += ingredients.water
        this.milk += ingredients.milk
        this.coffee += ingredients.coffee
        this.disposableCups += ingredients.disposableCups
        return (Response(
            "Filled",
            Ingredients(this.water, this.milk, this.coffee, this.disposableCups)
        ))
    }

    fun take(): Response {
        val out = this.money
        this.money = 0
        return Response(
            "I gave you $out UAH!",
            Ingredients(this.water, this.milk, this.coffee, this.disposableCups)
        )
    }
}