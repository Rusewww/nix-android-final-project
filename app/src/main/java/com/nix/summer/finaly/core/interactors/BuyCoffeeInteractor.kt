package com.nix.summer.finaly.core.interactors

import com.nix.summer.finaly.core.entity.Coffee
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.data.repository.FakeActionRepositoryImplementation

class BuyCoffeeInteractor(private val repository: FakeActionRepositoryImplementation) {
    operator fun invoke(coffee: Coffee) : Response =
        with(repository.buy(coffee)){
            Response(responseString, ingredients)
        }
}