package com.develop.nowasteinmyfridge.data.repository

import com.develop.nowasteinmyfridge.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
}