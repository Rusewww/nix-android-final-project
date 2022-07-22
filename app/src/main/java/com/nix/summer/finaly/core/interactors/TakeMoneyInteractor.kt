package com.nix.summer.finaly.core.interactors

import com.nix.summer.finaly.core.entity.Response
import com.nix.summer.finaly.data.repository.FakeActionRepositoryImplementation

class TakeMoneyInteractor(private val repository: FakeActionRepositoryImplementation) {
    operator fun invoke(): Response =
        with(repository.take()) {
            Response(responseString, ingredients)
        }
}