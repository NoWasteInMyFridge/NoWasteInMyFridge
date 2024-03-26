package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.repository.AuthRepositoryImpl
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
) {
    suspend operator fun invoke() {
         authRepositoryImpl.logout()
    }
}