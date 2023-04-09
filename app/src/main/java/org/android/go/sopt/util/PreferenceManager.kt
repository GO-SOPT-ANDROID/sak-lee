package org.android.go.sopt.util

import android.content.Context
import androidx.core.content.edit
import org.android.go.sopt.App
import org.android.go.sopt.model.UserInfo


class PreferenceManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var isLogin: Boolean
        get() = prefs.getBoolean(BOOLEAN, false)
        set(value) = prefs.edit{putBoolean(BOOLEAN, value)}

    var id: String?
        get() = prefs.getString(ID, null)
        set(value) = prefs.edit { putString(ID, value) }

    var pwd: String?
        get() = prefs.getString(PWD, null)
        set(value) = prefs.edit { putString(PWD, value) }

    var name: String?
        get() = prefs.getString(NAME, null)
        set(value) = prefs.edit { putString(NAME, value) }

    var specialty: String?
        get() = prefs.getString(SPECIALTY, null)
        set(value) = prefs.edit { putString(SPECIALTY, value) }

    fun saveUserInfo(userInfo: UserInfo?) {
        id = userInfo?.id
        pwd = userInfo?.pwd
        name = userInfo?.name
        specialty = userInfo?.specialty
    }

    fun getUserInfo(): UserInfo {
        return UserInfo(id.toString(), pwd.toString(), name.toString(), specialty.toString())
    }

    private companion object {
        const val PREFS_NAME = "preferences"
        const val BOOLEAN = "boolean"
        const val ID = "id"
        const val PWD = "pwd"
        const val NAME = "name"
        const val SPECIALTY = "specialty"
    }
}