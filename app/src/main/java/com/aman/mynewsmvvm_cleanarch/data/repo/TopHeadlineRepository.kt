package com.aman.mynewsmvvm_cleanarch.data.repo

import com.aman.mynewsmvvm_cleanarch.data.local.entity.Article
import com.aman.mynewsmvvm_cleanarch.data.api.NetworkService
import com.aman.mynewsmvvm_cleanarch.data.local.dao.TopHeadLineDao
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class TopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val topHeadLineDao: TopHeadLineDao
) {
    fun getTopHeadLines(country: String): Flow<List<APIArticle>>{
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
    fun saveTopHeadlinesArticles(articles: List<Article>, country: String): Flow<List<Long>>{
        return flow {
            emit(topHeadLineDao.insertAndDeleteTopHeadLineArticles(country,articles))
        }
    }
    fun getAllTopHeadlineArticles(country: String): Flow<List<Article>> {
        return topHeadLineDao.getAllTopHeadlinesArticle(country)
    }
}