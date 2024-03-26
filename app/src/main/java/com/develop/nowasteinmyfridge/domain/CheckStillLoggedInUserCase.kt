package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class CheckStillLoggedInUserCase @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
) {
    operator fun invoke(): Boolean {
        return authRepositoryImpl.checkStillLoggedIn()
    }
}