package org.android.go.sopt.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.android.go.sopt.data.Api.HomeApiService
import org.android.go.sopt.data.model.home.ResponseUserInfo

class UserPagingSource (
    private val apiService: HomeApiService,
) : PagingSource<Int, ResponseUserInfo>() {

    override fun getRefreshKey(state: PagingState<Int, ResponseUserInfo>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseUserInfo> {
        val position = params.key ?: 0
        return try {
            val userList = apiService.getMainPage(position).body()?.data
            LoadResult.Page(
                data = userList!!,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (userList.isEmpty()) null else position + 1
            )

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}