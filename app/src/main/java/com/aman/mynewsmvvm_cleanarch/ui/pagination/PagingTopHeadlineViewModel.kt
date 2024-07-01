package com.aman.mynewsmvvm_cleanarch.ui.pagination


import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import com.aman.mynewsmvvm_cleanarch.data.repo.PagingTopHeadlineRepository
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseViewModel
import com.aman.mynewsmvvm_cleanarch.utils.AppConstant
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PagingTopHeadlineViewModel @Inject constructor(
  topHeadlineRepository: PagingTopHeadlineRepository,
  networkHelper: NetworkHelper
): BaseViewModel<List<*>>(networkHelper) {

   val pagingDataFlow: Flow<PagingData<APIArticle>>

    init {
    pagingDataFlow = topHeadlineRepository.getTopHeadline(country = AppConstant.COUNTRY)
        .cachedIn(viewModelScope)
    }



}