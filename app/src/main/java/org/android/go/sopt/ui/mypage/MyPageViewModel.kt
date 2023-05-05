package org.android.go.sopt.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.util.User

class MyPageViewModel : ViewModel() {
    private val _isLogin = MutableLiveData<Boolean>(User.isLoggedIn.value)
    val isLogin: LiveData<Boolean> = _isLogin

    fun logout(boolean: Boolean) {
        _isLogin.value = boolean
        User.logout()
    }
}