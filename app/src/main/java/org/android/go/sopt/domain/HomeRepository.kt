package org.android.go.sopt.domain

import org.android.go.sopt.data.model.UserList
import retrofit2.Response

interface HomeRepository {
    suspend fun getUserList(): Response<UserList>
}