package com.aman.mynewsmvvm_cleanarch.ui.topheadline

import androidx.lifecycle.viewModelScope
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.asEntity
import com.aman.mynewsmvvm_cleanarch.data.repo.TopHeadlineRepository
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseViewModel
import com.aman.mynewsmvvm_cleanarch.utils.AppConstant
import com.aman.mynewsmvvm_cleanarch.utils.DispatcherProvider
import com.aman.mynewsmvvm_cleanarch.utils.Resource
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel<List<*>>(networkHelper){

init {
    startFetchingNews()
}

    fun startFetchingNews() {
        if (checkInternetConnection())
            fetchNews()
        else
            fetchNewsFromDatabase()
    }

    private fun fetchNews() {
        viewModelScope.launch(dispatcherProvider.main) {
            topHeadlineRepository.getTopHeadLines(country = AppConstant.COUNTRY).map {
                it.map { articleApi ->
                    articleApi.asEntity(AppConstant.COUNTRY) }.toList()
            }.flatMapConcat {
                return@flatMapConcat topHeadlineRepository.saveTopHeadlinesArticles(
                    it, AppConstant.COUNTRY
                )
            }.flowOn(dispatcherProvider.io).catch { e ->
                println("Exception $e")
                _data.value = Resource.error(e.toString())
            }.collect {
                fetchNewsFromDatabase()
            }
        }
    }

    private fun fetchNewsFromDatabase() {
    viewModelScope.launch(dispatcherProvider.main) {
        topHeadlineRepository.getAllTopHeadlineArticles(AppConstant.COUNTRY)
            .flowOn(dispatcherProvider.io).catch { e->
                _data.value = Resource.error(e.toString())
            }.collect{
                if(!checkInternetConnection() && it.isEmpty())
                    _data.value = Resource.error("Data Not Found")
                else
                    _data.value = Resource.success(it)
            }
    }
    }

}