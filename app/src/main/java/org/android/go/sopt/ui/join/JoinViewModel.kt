package org.android.go.sopt.ui.join

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.domain.SignRepository
import org.android.go.sopt.model.RequestSignInDto
import org.android.go.sopt.model.RequestSignUpDto
import org.android.go.sopt.util.Constants.ID_COUNT_MAX
import org.android.go.sopt.util.Constants.ID_COUNT_MIN
import org.android.go.sopt.util.Constants.ID_REGEX_MSG
import org.android.go.sopt.util.Constants.INPUT_SUCCESS
import org.android.go.sopt.util.Constants.NULL_JOIN
import org.android.go.sopt.util.Constants.PWD_CHECK
import org.android.go.sopt.util.Constants.PWD_COUNT_MAX
import org.android.go.sopt.util.Constants.PWD_COUNT_MIN
import org.android.go.sopt.util.Constants.PWD_REGEX_MSG
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class JoinViewModel @Inject constructor(
    private val apiRepository: SignRepository
) : ViewModel() {
    /**
     * id,pwd text 입력 제약 조건 설정
     * 제약 조건의 따른 안내문구
     * **/
    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _pwd = MutableLiveData<String>()
    val pwd: LiveData<String> = _pwd

    private val _pwdCheck = MutableLiveData<String>()
    val pwdCheck: LiveData<String> = _pwdCheck

    private val _sign_Intro_Msg = MutableLiveData("")
    val sign_Intro_Msg: LiveData<String> = _sign_Intro_Msg

    private fun checkIdLength(id: String) =
        id.isNullOrEmpty() || id.length in ID_COUNT_MIN..ID_COUNT_MAX

    private fun checkPwdLength(pwd: String) =
        pwd.isNullOrEmpty() || pwd.length in PWD_COUNT_MIN..PWD_COUNT_MAX

    private fun checkPwd(pwd: String, pwdCheck: String) = pwd == pwdCheck || pwd.isNullOrEmpty()

    private fun hasBlank(
        id: String,
        pw: String,
        pwdCheck: String,
        name: String,
        specialty: String
    ): Boolean {
        return id.isBlank() || pw.isBlank() || pwdCheck.isBlank() || name.isBlank() || specialty.isBlank()
    }

    fun loginDataChanged(
        id: String, pwd: String, pwdCheck: String, name: String,
        specialty: String
    ) {
        when {
            checkIdLength(id).not() -> _sign_Intro_Msg.value = ID_REGEX_MSG
            checkPwdLength(pwd).not() -> _sign_Intro_Msg.value = PWD_REGEX_MSG
            checkPwd(pwd, pwdCheck).not() -> _sign_Intro_Msg.value = PWD_CHECK
            hasBlank(id, pwd, pwdCheck, name, specialty) -> _sign_Intro_Msg.value = NULL_JOIN
            else -> _sign_Intro_Msg.value = INPUT_SUCCESS
        }
    }

    fun signUp(requestSignUpDto: RequestSignUpDto) = viewModelScope.launch {
        val response = apiRepository.singUp(requestSignUpDto)
        if (response.isSuccessful) {
            Log.d("test", "test")
        } else {
            Log.d("test", "test")
        }
    }

    fun signIn(requestSignInDto: RequestSignInDto) = viewModelScope.launch {
        val response = apiRepository.singIn(requestSignInDto)
        if (response.isSuccessful) {
            Log.d("test", "test")
        } else {
            Log.d("test", "test")
        }
    }

}