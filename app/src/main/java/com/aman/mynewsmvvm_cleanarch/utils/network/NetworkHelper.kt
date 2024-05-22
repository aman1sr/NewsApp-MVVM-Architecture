package com.aman.mynewsmvvm_cleanarch.utils.network

import androidx.annotation.VisibleForTesting

interface NetworkHelper {

    fun isNetworkConnected(): Boolean

    fun castToNetworkError(throwable: Throwable): NetworkError

    @VisibleForTesting
    fun setStatus(status: Boolean)
}