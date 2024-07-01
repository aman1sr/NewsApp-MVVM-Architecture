package com.aman.mynewsmvvm_cleanarch.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aman.mynewsmvvm_cleanarch.data.api.NetworkService
import com.aman.mynewsmvvm_cleanarch.data.local.dao.TopHeadLineDao
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ViewModelScoped
class PagingTopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val topHeadLineDao: TopHeadLineDao
) {
    companion object{
        const val NETWORK_PAGE_SIZE = 10
    }

    fun getTopHeadline(country: String): Flow<PagingData<APIArticle>>{
        return Pager(config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false
        ), pagingSourceFactory = {
            TopHeadlinePagingSource(networkService,country)
        }).flow
    }

}