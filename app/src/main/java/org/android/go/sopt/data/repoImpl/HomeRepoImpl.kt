package org.android.go.sopt.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.android.go.sopt.data.Api.HomeApiService
import org.android.go.sopt.data.datasource.UserPagingSource
import org.android.go.sopt.data.model.home.ResponseUserInfo
import org.android.go.sopt.domain.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(
    private val apiService: HomeApiService
) : HomeRepository {
    override fun getUserList(): Flow<PagingData<ResponseUserInfo>> =
        Pager(PagingConfig(10)) {
            UserPagingSource(apiService)
        }.flow




}