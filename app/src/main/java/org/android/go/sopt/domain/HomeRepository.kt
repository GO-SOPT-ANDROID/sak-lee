package org.android.go.sopt.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.go.sopt.data.model.ResponseUserInfo

interface HomeRepository {
    fun getUserList(): Flow<PagingData<ResponseUserInfo>>
}