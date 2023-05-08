package org.android.go.sopt.data.repoImpl

import org.android.go.sopt.data.Api.HomeApiService
import org.android.go.sopt.data.model.UserList
import org.android.go.sopt.domain.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val apiService: HomeApiService
) : HomeRepository {
    override suspend fun getUserList(): Response<UserList> =
        apiService.getMainPage(2)

}