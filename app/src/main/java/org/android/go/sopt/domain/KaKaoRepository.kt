package org.android.go.sopt.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import org.android.go.sopt.data.model.kakao.KaKaoImage

interface KaKaoRepository {
    fun getKaKaoResult(query:String): Flow<PagingData<KaKaoImage>>
}