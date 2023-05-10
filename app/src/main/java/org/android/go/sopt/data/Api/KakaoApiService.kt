package org.android.go.sopt.data.Api

import org.android.go.sopt.data.model.kakao.ResponsKaKao
import org.android.go.sopt.util.API.SEARCH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface KakaoApiService {

    @GET(SEARCH)
    suspend fun getKaKaoSearch(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ResponsKaKao>
}