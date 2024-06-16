package com.aman.mynewsmvvm_cleanarch.di.module

import com.aman.mynewsmvvm_cleanarch.ui.pagination.PagingTopHeadlineAdapter
import com.aman.mynewsmvvm_cleanarch.ui.topheadline.TopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import java.util.ArrayList

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    fun providePagingTopHeadlineAdapter() = PagingTopHeadlineAdapter()

    @Provides
    fun provideTopHeadLineAdapter() = TopHeadlineAdapter(ArrayList())
}