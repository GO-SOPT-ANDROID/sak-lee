package org.android.go.sopt.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.sign.RequestSignInDto
import org.android.go.sopt.domain.SignRepository
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiRepository: SignRepository
) : ViewModel() {
    private val _signInResult = MutableLiveData(false)
    val signInResult : LiveData<Boolean> = _signInResult

    fun signIn(requestSignInDto: RequestSignInDto) = viewModelScope.launch {
        val response = apiRepository.singIn(requestSignInDto)
        _signInResult.value = response.isSuccessful
    }
}