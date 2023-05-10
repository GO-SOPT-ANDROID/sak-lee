package org.android.go.sopt.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T : Any> pagingSubmitData(
    lifecycleOwner: LifecycleOwner,
    getData: Flow<PagingData<T>>,
    pagingAdapter: PagingDataAdapter<T, *>
) {
    lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                getData.collectLatest { pagingData ->
                    pagingAdapter.submitData(pagingData)
                }
            }
        }
    }
}

object PagingSourceUtils {
    suspend fun <T : Any> getPagingSource(
        apiCall: suspend (query: String, page: Int) -> T,
        query: String,
        transform: (T) -> List<T>
    ): PagingSource<Int, T> {
        return object : PagingSource<Int, T>() {
            override fun getRefreshKey(state: PagingState<Int, T>): Int? {
                return state.anchorPosition?.let { position ->
                    state.closestPageToPosition(position)?.prevKey?.plus(1)
                        ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
                }
            }

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
                val position = params.key ?: 1
                return try {
                    val result = apiCall(query, position)
                    val data = transform(result)
                    LoadResult.Page(
                        data = data,
                        prevKey = if (position == 1) null else position - 1,
                        nextKey = if (data.isEmpty()) null else position + 1
                    )
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }
        }
    }
}