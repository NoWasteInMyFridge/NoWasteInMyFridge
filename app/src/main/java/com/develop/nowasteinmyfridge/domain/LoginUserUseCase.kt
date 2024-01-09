package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.repository.AuthRepositoryImpl
import com.develop.nowasteinmyfridge.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Flow<Resource<AuthResult>> {
        return authRepositoryImpl.loginUser(
            email = email,
            password = password,
        )
    }
}