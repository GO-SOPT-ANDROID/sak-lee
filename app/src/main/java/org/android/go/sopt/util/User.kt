package org.android.go.sopt.util

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.android.go.sopt.App
import org.android.go.sopt.data.model.UserInfo

object User {

    var user: MutableLiveData<UserInfo>? = null
        private set

    val isLoggedIn = MutableLiveData(user != null)

    fun login(userInfo: UserInfo) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                setUser(
                    UserInfo(
                        id = userInfo.id,
                        pwd = userInfo.pwd,
                        name = userInfo.name,
                        specialty = userInfo.specialty
                    )
                )
                isLoggedIn.value = true
            }
        }

    }

    fun logout() {
        CoroutineScope(Dispatchers.Main).launch {
            isLoggedIn.value = false
            App.prefs.isLogin = false
            App.prefs.saveUserInfo(null)
            user = null
        }
        Log.d("test", "User - logout Success!")
    }

    private fun setUser(userData: UserInfo) {
        if (user != null) {
            user!!.value = userData
        } else {
            user = MutableLiveData(userData)
        }
    }
}