package org.android.go.sopt.util

import org.android.go.sopt.BuildConfig

object Constants {

    const val ID_COUNT_MIN = 6
    const val PWD_COUNT_MIN = 8
    const val ID_COUNT_MAX = 10
    const val PWD_COUNT_MAX = 12
    const val ID_REGEX_MSG = "ID 6~10자리"
    const val PWD_REGEX_MSG = "PassWord 8~12자리"
    const val PWD_CHECK = "Password가 다릅니다."
    const val NULL_JOIN = "빈칸이 있습니다."
    const val INPUT_SUCCESS = "Id,Pwd good!"

    const val ID_REGEX = ""
    const val PW_REGEX = ""
}

object API {
    const val API_TAG = "Retrofit2"
    const val BASE_URL = BuildConfig.AUTH_BASE_URL
    const val SIGN_IN = "sign-in"
    const val SIGN_UP = "sign-up"
}