package com.aman.mynewsmvvm_cleanarch.data.api

import com.aman.mynewsmvvm_cleanarch.data.newssources.NewsSourcesResponse
import com.aman.mynewsmvvm_cleanarch.data.newssources.TopHeadlinesResponse

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String, @Query("page") page: Int = 1, @Query("pageSize") pageSize : Int = 20): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSources(): NewsSourcesResponse

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsByLanguage(@Query("language") languageCode: String): TopHeadlinesResponse

    @GET("everything")
    suspend fun getNewsByQueries(@Query("q") queries: String): TopHeadlinesResponse

}