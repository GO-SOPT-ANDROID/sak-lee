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

    const val ID_REGEX = """^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{$ID_COUNT_MIN,$ID_COUNT_MAX}\$"""
    const val PW_REGEX = """^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^+\-=])(?=\S{$PWD_COUNT_MIN,$PWD_COUNT_MAX}${'$'}).*$"""
}

object API {
    const val API_TAG = "Retrofit2"
    const val BASE_URL = BuildConfig.AUTH_BASE_URL
    const val REQRES_BASE_URL = BuildConfig.REQRES_BASE_URL
    const val KAKAO_BASE_URL = BuildConfig.KAKAO_BASE_URL
    const val KAKAO_REST_API = BuildConfig.KAKAO_REST_API
    const val SIGN_IN = "sign-in"
    const val SIGN_UP = "sign-up"
    const val SEARCH = "/v2/search/image"
    const val HOME_USER = "api/users"
    const val UPLOAD = "upload"
}