package org.android.go.sopt.data.Api


import org.android.go.sopt.data.model.UserList
import org.android.go.sopt.util.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.security.cert.CertStoreSpi

interface HomeApiService {

    @GET("api/users")
    suspend fun getMainPage(
        @Query("page") page: Int,
    ): Response<UserList>
}