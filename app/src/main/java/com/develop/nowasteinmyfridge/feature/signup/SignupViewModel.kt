package com.develop.nowasteinmyfridge.feature.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.UserCreate
import com.develop.nowasteinmyfridge.domain.CreateUserUseCase
import com.develop.nowasteinmyfridge.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel() {
    private val _createUserResult = MutableStateFlow<Result<Unit>?>(null)
    val createUserResult: StateFlow<Result<Unit>?>
        get() = _createUserResult

    fun createUser(userCreate: UserCreate) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _createUserResult.value = Result.Loading
                createUserUseCase.invoke(userCreate)
                _createUserResult.value = Result.Success(Unit)

            } catch (e: Exception) {
                _createUserResult.value = Result.Error(e)
            }
        }
    }
}