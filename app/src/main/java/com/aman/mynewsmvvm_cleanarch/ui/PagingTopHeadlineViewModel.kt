package com.aman.mynewsmvvm_cleanarch.ui

import android.util.Log
import com.aman.mynewsmvvm_cleanarch.data.repo.PagingTopHeadlineRepository
import com.aman.mynewsmvvm_cleanarch.ui.base.BaseViewModel
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingTopHeadlineViewModel @Inject constructor(           // todo: inject Repo  and NetworkHelper instance  ,,, so to make it runnable
    topHeadlineRepository: PagingTopHeadlineRepository, networkHelper: NetworkHelper
): BaseViewModel<List<*>>(networkHelper) {
//    val pagingDataFlow: Flow<PagingData<APIArticle>>

    init {
//        pagingDataFlow = topHeadlineRepository
        Log.d(PaginationTopHeadlineActivity.TAG, "VM init ")
    }
}