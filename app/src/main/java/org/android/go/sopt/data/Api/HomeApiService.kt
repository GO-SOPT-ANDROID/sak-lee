package org.android.go.sopt.data.Api


import org.android.go.sopt.data.model.home.UserList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {

    @GET("api/users")
    suspend fun getMainPage(
        @Query("page") page: Int,
    ): Response<UserList>
}