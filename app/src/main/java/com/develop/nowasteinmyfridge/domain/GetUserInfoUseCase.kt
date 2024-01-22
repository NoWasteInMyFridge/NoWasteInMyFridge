package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.UserProfile
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepositoryImpl
import com.develop.nowasteinmyfridge.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val firebaseFirestoreRepositoryImpl: FirebaseFirestoreRepositoryImpl,
) {
    suspend operator fun invoke():Flow<Result<UserProfile>> {
        return firebaseFirestoreRepositoryImpl.getUserInfo()
    }
}