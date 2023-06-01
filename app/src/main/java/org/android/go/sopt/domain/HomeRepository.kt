package org.android.go.sopt.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import org.android.go.sopt.data.model.home.ResponseUserInfo
import retrofit2.Response

interface HomeRepository {
    fun getUserList(): Flow<PagingData<ResponseUserInfo>>

  }