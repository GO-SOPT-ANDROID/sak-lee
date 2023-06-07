package org.android.go.sopt.ui.join

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.android.go.sopt.data.model.sign.RequestSignUpDto
import org.android.go.sopt.domain.SignRepository
import org.android.go.sopt.util.Constants.ID_COUNT_MAX
import org.android.go.sopt.util.Constants.ID_COUNT_MIN
import org.android.go.sopt.util.Constants.ID_REGEX
import org.android.go.sopt.util.Constants.ID_REGEX_MSG
import org.android.go.sopt.util.Constants.INPUT_SUCCESS
import org.android.go.sopt.util.Constants.NULL_JOIN
import org.android.go.sopt.util.Constants.PWD_CHECK
import org.android.go.sopt.util.Constants.PWD_COUNT_MAX
import org.android.go.sopt.util.Constants.PWD_COUNT_MIN
import org.android.go.sopt.util.Constants.PWD_REGEX_MSG
import org.android.go.sopt.util.Constants.PW_REGEX
import org.android.go.sopt.util.ContentUriRequestBody
import java.util.regex.Pattern
import javax.inject.Inject


@dagger.hilt.android.lifecycle.HiltViewModel
class JoinViewModel @Inject constructor(
    private val apiRepository: SignRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()

        val loginFormState: LiveData<LoginFormState> get() = _loginForm
//    val loginFormState: LiveData<LoginFormState>
//        get() = _loginForm.map {
//            val id = id.value ?: ""
//            val pwd = pwd.value ?: ""
//            val pwdCheck = pwdCheck.value
//            when {
//                isIdValid(id).not() -> LoginFormState(idError = ID_REGEX_MSG)
//
//                isPwdValid(pwd).not() -> LoginFormState(pwError = ID_REGEX_MSG)
//                checkPwd(pwd, pwdCheck).not() -> LoginFormState(pwCheckError = PWD_CHECK)
//                else -> LoginFormState(isDataValid = true)
//            }
//        }

    val id = MutableLiveData<String>()

    val pwd = MutableLiveData<String>()

    val pwdCheck = MutableLiveData<String>()

    val name = MutableLiveData<String>()

    val specialty = MutableLiveData<String>()

    private val _signUpResult = MutableLiveData(false)
    val signUpResult: LiveData<Boolean> = _signUpResult

    private val idPattern: Pattern = Pattern.compile(ID_REGEX)
    private val pwdPattern: Pattern = Pattern.compile(PW_REGEX)

    private val _image = MutableLiveData<ContentUriRequestBody>()
    val image: LiveData<ContentUriRequestBody>
        get() = _image

    fun uploadImage(file: MultipartBody.Part) = viewModelScope.launch {
        val response = apiRepository.uploadImage(file)
        if (response.isSuccessful) Log.d("test", "success")
    }

    fun setRequestBody(requestBody: ContentUriRequestBody) {
        _image.value = requestBody
    }

    private fun checkPwd(pwd: String?, pwdCheck: String?) = pwd == pwdCheck || pwd.isNullOrEmpty()
    private fun isIdValid(id: String) = id.isBlank() || idPattern.matcher(id).matches()
    private fun isPwdValid(pwd: String) = pwd.isBlank() || pwdPattern.matcher(pwd).matches()

    private fun hasBlank(
        id: String,
        pw: String,
        pwdCheck: String,
        name: String,
        specialty: String
    ): Boolean {
        return id.isBlank() || pw.isBlank() || pwdCheck.isBlank() || name.isBlank() || specialty.isBlank()
    }


    fun loginDataChanged() {
        val id = id.value ?: ""
        val pwd = pwd.value ?: ""
        val pwdCheck = pwdCheck.value
        when {
            isIdValid(id).not() -> _loginForm.value = LoginFormState(idError = ID_REGEX_MSG)
            isPwdValid(pwd).not() -> _loginForm.value = LoginFormState(pwError = PWD_REGEX_MSG)
            checkPwd(pwd, pwdCheck).not() -> _loginForm.value =
                LoginFormState(pwCheckError = PWD_CHECK)
            else -> _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun signUp() = viewModelScope.launch {
        val response = apiRepository.singUp(
            RequestSignUpDto(
                id.value ?: "",
                pwd.value ?: "",
                name.value ?: "",
                specialty.value ?: ""
            )
        )
        _signUpResult.value = response.isSuccessful
    }

}