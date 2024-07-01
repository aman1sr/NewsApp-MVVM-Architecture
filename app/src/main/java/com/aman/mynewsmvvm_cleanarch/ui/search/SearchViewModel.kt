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

    /*
    * debounce: used to wait for a specified amount of time  before emitting the latest value.
    * filter: filters the flow based on a condition & prevents further processing of this flow by returning false
    *distinctUntilChanged: operator ensures that only distinct consecutive values are emitted. It prevents the flow from emitting the same query multiple times if the user repeatedly enters the same text.
    * flatMapLatest: (v imp)   transforms the latest value emitted by the flow into another flow. If a new query comes in before the previous flow has finished processing, it cancels the previous flow and starts a new one.
    * catch : used to handle any exceptions that occur during the flow's emission
    * */
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
                }.flowOn(dispatcherProvider.io).collect {
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