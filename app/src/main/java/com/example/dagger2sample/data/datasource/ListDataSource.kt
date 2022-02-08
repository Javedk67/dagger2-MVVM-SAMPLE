package com.example.dagger2sample.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dagger2sample.api.ApiService
import com.example.dagger2sample.data.model.MovieBean
import com.example.dagger2sample.utils.STARTING_PAGE
import java.io.IOException

class ListDataSource (private val apiService: ApiService) :
    PagingSource<Int, MovieBean>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieBean> {
        val page = params.key ?: STARTING_PAGE

        return try {
            val data = apiService.getList("1d094e25","fast", "movie", page)
            LoadResult.Page(
                data = data.Search,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (data.Search.isEmpty()) null else page + 1
            )

        } catch (throwable: Throwable) {
            var exception = throwable
            if (throwable is IOException) {
                exception = IOException("Please check internet connection")
            }
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieBean>): Int? {

        return state.anchorPosition

    }

}