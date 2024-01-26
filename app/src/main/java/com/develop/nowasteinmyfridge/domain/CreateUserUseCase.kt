package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.UserCreate
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepositoryImpl
import com.develop.nowasteinmyfridge.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val firebaseFirestoreRepositoryImpl: FirebaseFirestoreRepositoryImpl,
) {

    suspend operator fun invoke(userCreate: UserCreate): Flow<Result<Unit>> {
        return firebaseFirestoreRepositoryImpl.createUser(userCreate)
    }
}