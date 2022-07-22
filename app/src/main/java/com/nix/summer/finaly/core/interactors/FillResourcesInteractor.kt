package com.nix.summer.finaly.core.interactors

import com.nix.summer.finaly.core.entity.Ingredients
import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.data.repository.FakeActionRepositoryImplementation

class FillResourcesInteractor(private val repository: FakeActionRepositoryImplementation) {
    operator fun invoke(ingredient: Ingredients) : Response =
        with(repository.fill(ingredient)){
            Response(responseString, ingredients)
        }
}