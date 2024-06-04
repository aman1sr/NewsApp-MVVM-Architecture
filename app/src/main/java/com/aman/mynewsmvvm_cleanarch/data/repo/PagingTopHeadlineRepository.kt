package com.aman.mynewsmvvm_cleanarch.data.repo

import com.aman.mynewsmvvm_cleanarch.data.api.NetworkService
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class PagingTopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService
) {
    fun getTestHeadline(country: String): Flow<List<APIArticle>> {
        return flow { emit(networkService.getTopHeadlines(country)) }
            .map { it.articles }
    }

}