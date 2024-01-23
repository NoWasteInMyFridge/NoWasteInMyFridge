package com.develop.nowasteinmyfridge.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.domain.LoginUserUseCase
import com.develop.nowasteinmyfridge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {

    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUserUseCase.invoke(
                email = email,
                password = password,
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _signInState.send(SignInState(isSuccess = "Sign In Success"))
                    }

                    is Resource.Loading -> {
                        _signInState.send(SignInState(isLoading = true))
                    }

                    is Resource.Error -> {
                        _signInState.send(SignInState(isError = result.message))
                    }
                }
            }
        }
    }
}
