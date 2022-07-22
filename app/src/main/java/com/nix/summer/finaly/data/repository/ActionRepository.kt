package com.nix.summer.finaly.data.repository

import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Response

interface ActionRepository {
    fun buy(coffeeType: Coffee): Response

    fun fill(ingredients: Ingredients): Response

    fun take(): Response
}