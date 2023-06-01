package org.android.go.sopt.data.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.go.sopt.data.Api.KakaoApiService
import org.android.go.sopt.data.datasource.KaKaoPagingSource
import org.android.go.sopt.data.model.kakao.KaKaoImage
import org.android.go.sopt.domain.KaKaoRepository
import javax.inject.Inject

class KaKaoRepoImpl @Inject constructor(
    private val apiService: KakaoApiService
) : KaKaoRepository {
    override fun getKaKaoResult(query: String): Flow<PagingData<KaKaoImage>> =
        Pager(PagingConfig(10)) {
            KaKaoPagingSource(apiService, query)
        }.flow
}