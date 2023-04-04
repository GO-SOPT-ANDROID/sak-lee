package org.android.go.sopt.util

import android.content.Context
import org.android.go.sopt.App
import org.android.go.sopt.model.UserInfo


class PreferenceManager(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getId(): String? = prefs.getString(ID, null)

    private fun saveId(id: String?) = prefs.edit().putString(ID, id).apply()

    fun getPwd(): String? = prefs.getString(PWD, null)

    private fun savePwd(pwd: String?) = prefs.edit().putString(PWD, pwd).apply()

    private fun getName(): String? = prefs.getString(NAME, null)

    private fun saveName(name: String?) = prefs.edit().putString(NAME, name).apply()

    private fun getSpecialty(): String? = prefs.getString(SPECIALTY, null)


    private fun saveSpecialty(specialty: String?) =
        prefs.edit().putString(SPECIALTY, specialty).apply()

    fun saveUserInfo(userInfo: UserInfo?) {
        App.prefs.saveId(userInfo?.id)
        App.prefs.savePwd(userInfo?.pwd)
        App.prefs.saveName(userInfo?.name)
        App.prefs.saveSpecialty(userInfo?.specialty)
    }

    fun getUserInfo(): UserInfo {
        return UserInfo(
            App.prefs.getId().toString(),
            App.prefs.getPwd().toString(),
            App.prefs.getName().toString(),
            App.prefs.getSpecialty().toString()
        )
    }

    private companion object {
        const val PREFS_NAME = "preferences"
        const val ID = "id"
        const val PWD = "pwd"
        const val NAME = "name"
        const val SPECIALTY = "specialty"
    }
}