package org.android.go.sopt.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.android.go.sopt.data.Api.KakaoApiService
import org.android.go.sopt.data.model.home.ResponseUserInfo
import org.android.go.sopt.data.model.kakao.KaKaoImage

class KaKaoPagingSource(
    private val apiService: KakaoApiService,
    private val query: String
) : PagingSource<Int, KaKaoImage>() {

    override fun getRefreshKey(state: PagingState<Int, KaKaoImage>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, KaKaoImage>  {
        val position = params.key ?: 1
        return runCatching {
            val kakaoResult =
                apiService.getKaKaoSearch(query, sort = "accuracy", position, size = 10)
                    .body()?.documents
            LoadResult.Page(
                data = kakaoResult!!,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (kakaoResult.isEmpty()) null else position + 1
            )
        }.getOrElse {
            LoadResult.Error(it)
        }
    }
}