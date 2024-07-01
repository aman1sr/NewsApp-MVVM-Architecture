package com.aman.mynewsmvvm_cleanarch.data.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aman.mynewsmvvm_cleanarch.data.api.NetworkService
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import com.aman.mynewsmvvm_cleanarch.data.repo.PagingTopHeadlineRepository.Companion.NETWORK_PAGE_SIZE
import okio.IOException
import retrofit2.HttpException

private const val STARTING_KEY = 1

class TopHeadlinePagingSource(
    private val networkService: NetworkService,
    private val country: String
) : PagingSource<Int, APIArticle>() {

    // getRefreshKey(): This method is called when the Paging library needs to reload items for the UI because the data in its backing PagingSource has changed.
    override fun getRefreshKey(state: PagingState<Int, APIArticle>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    // load(): function will be called by the Paging library to asynchronously fetch more data to be displayed as the user scrolls around.
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, APIArticle> {
        val position = params.key ?: STARTING_KEY
        return try {
            val response = networkService.getTopHeadlines(
                country = country, page = position, pageSize = params.loadSize
            )
            val articles = response.articles
            val nextKey = if (articles.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }

            LoadResult.Page(
                data = articles,
                prevKey = if (position == STARTING_KEY) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}