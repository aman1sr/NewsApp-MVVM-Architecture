package com.aman.mynewsmvvm_cleanarch.di.module

import com.aman.mynewsmvvm_cleanarch.ui.pagination.PagingTopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    fun providePagingTopHeadlineAdapter() = PagingTopHeadlineAdapter()
}