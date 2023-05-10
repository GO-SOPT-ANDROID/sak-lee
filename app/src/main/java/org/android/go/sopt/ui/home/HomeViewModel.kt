package org.android.go.sopt.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.go.sopt.data.model.home.ResponseUserInfo
import org.android.go.sopt.domain.HomeRepository
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class HomeViewModel  @Inject constructor(
    private val apiRepository: HomeRepository
) : ViewModel() {
    fun getUserList(): Flow<PagingData<ResponseUserInfo>> = apiRepository.getUserList()
}