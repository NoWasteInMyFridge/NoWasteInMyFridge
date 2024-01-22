package com.develop.nowasteinmyfridge.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.UserProfile
import com.develop.nowasteinmyfridge.domain.GetUserInfoUseCase
import com.develop.nowasteinmyfridge.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {

    private val _userProfileInfoState = MutableStateFlow<Result<UserProfile>>(Result.Loading)
    val userProfileInfoState: StateFlow<Result<UserProfile>>
        get() = _userProfileInfoState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getUserInfo()
        }
    }

    private suspend fun getUserInfo() {
        getUserInfoUseCase.invoke()
            .collect { result ->
                _userProfileInfoState.value = result
            }
    }
}
