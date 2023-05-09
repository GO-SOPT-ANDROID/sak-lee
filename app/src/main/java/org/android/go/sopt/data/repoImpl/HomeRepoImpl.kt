package org.android.go.sopt.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.go.sopt.data.Api.HomeApiService
import org.android.go.sopt.data.datasource.UserPagingSource
import org.android.go.sopt.data.model.ResponseUserInfo
import org.android.go.sopt.domain.HomeRepository
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val apiService: HomeApiService
) : HomeRepository {
    override  fun getUserList(): Flow<PagingData<ResponseUserInfo>> =
        Pager(PagingConfig(10)){
            UserPagingSource(apiService)
        }.flow
}