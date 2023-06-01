package org.android.go.sopt.data.Api


import org.android.go.sopt.data.model.home.UserList
import org.android.go.sopt.util.API.HOME_USER
import retrofit2.Response
import retrofit2.http.*

interface HomeApiService {

    @GET(HOME_USER)
    suspend fun getMainPage(
        @Query("page") page: Int,
    ): Response<UserList>
}