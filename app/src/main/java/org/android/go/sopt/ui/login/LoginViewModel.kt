package org.android.go.sopt.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.sign.RequestSignInDto
import org.android.go.sopt.domain.SignRepository
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiRepository: SignRepository
) : ViewModel() {
    private val _signInResult = MutableStateFlow(false)
    val signInResult = _signInResult.asStateFlow()

    fun signIn(requestSignInDto: RequestSignInDto) = viewModelScope.launch {
        val response = apiRepository.singIn(requestSignInDto)
        _signInResult.value = response.isSuccessful
    }
}
