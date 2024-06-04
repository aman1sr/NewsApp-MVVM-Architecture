package com.aman.mynewsmvvm_cleanarch.di.module

import android.content.Context
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelper
import com.aman.mynewsmvvm_cleanarch.utils.network.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideNetworkConnection(@ApplicationContext context: Context): NetworkHelper =
        NetworkHelperImpl(context)


}