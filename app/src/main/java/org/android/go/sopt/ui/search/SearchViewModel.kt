package org.android.go.sopt.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.go.sopt.data.model.kakao.KaKaoImage
import org.android.go.sopt.domain.KaKaoRepository
import javax.inject.Inject

@dagger.hilt.android.lifecycle.HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiRepository: KaKaoRepository
) : ViewModel() {
    fun getKaKaoResult(query: String): Flow<PagingData<KaKaoImage>> =
        apiRepository.getKaKaoResult(query)
}