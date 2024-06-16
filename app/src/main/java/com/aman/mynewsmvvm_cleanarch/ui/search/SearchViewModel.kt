package com.aman.mynewsmvvm_cleanarch.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import com.aman.mynewsmvvm_cleanarch.data.repo.SearchRepository
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseViewModel
import com.aman.mynewsmvvm_cleanarch.utils.DispatcherProvider
import com.aman.mynewsmvvm_cleanarch.utils.Resource
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    networkHelper: NetworkHelper,
    private val dispatcherProvider: DispatcherProvider
): BaseViewModel<List<*>>(networkHelper) {

    // using for Eff Search Bar query 1
    fun setUpSearchStateFlow(searchFlow: StateFlow<String>) {
        viewModelScope.launch(dispatcherProvider.main) {
            searchFlow.debounce(2000)
                .filter { query ->
                    if (query.isEmpty()) {
                        _data.value = Resource.success(listOf<APIArticle>())
                        return@filter false
                    } else {
                        return@filter true
                    }
                }.distinctUntilChanged().flatMapLatest { query ->
                    // logd
                    searchRepository.getNewsByQueries(sources = query).catch { e ->
                        _data.value = Resource.error(e.toString())
                    }
                }.flowOn(dispatcherProvider.io).collect{
                        _data.value = Resource.success(it)
                    }
                }
        }

    // using for TryAgain purpose
    fun fetchNewsByQueries(queries: String){
        viewModelScope.launch(dispatcherProvider.main) {
            searchRepository.getNewsByQueries(sources = queries)
                .catch { e ->
                    _data.value = Resource.error(e.toString())
                }.flowOn(dispatcherProvider.io)
                .collect{
                    _data.value = Resource.success(it)
                }
        }
    }
}