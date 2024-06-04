package com.aman.mynewsmvvm_cleanarch.data.newssources

import com.aman.mynewsmvvm_cleanarch.data.newssources.APINewsSource
import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("sources") val newsSource: List<APINewsSource> = arrayListOf(),
)
