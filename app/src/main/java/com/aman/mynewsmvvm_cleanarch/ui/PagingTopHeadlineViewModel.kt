package com.aman.mynewsmvvm_cleanarch.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.aman.mynewsmvvm_cleanarch.data.repo.PagingTopHeadlineRepository
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseViewModel
import com.aman.mynewsmvvm_cleanarch.utils.AppConstant
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingTopHeadlineViewModel @Inject constructor(           // todo: inject Repo  and NetworkHelper instance  ,,, so to make it runnable
  private val topHeadlineRepository: PagingTopHeadlineRepository, networkHelper: NetworkHelper
): BaseViewModel<List<*>>(networkHelper) {
//    val pagingDataFlow: Flow<PagingData<APIArticle>>

    init {
        Log.d(PaginationTopHeadlineActivity.TAG, "VM init ")
        fetchTestHeadLine()
    }
    fun fetchTestHeadLine(){
        viewModelScope.launch {
             topHeadlineRepository.getTestHeadline(AppConstant.COUNTRY)
                 .collect{
                 Log.d(PaginationTopHeadlineActivity.TAG, "fetchTestHeadLine: $it")
             }
        }
    }
}