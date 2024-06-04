package com.aman.mynewsmvvm_cleanarch.data.newssources

import com.aman.mynewsmvvm_cleanarch.data.model.topheadlines.APIArticle
import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(
    @SerializedName("status") val status: String = "",
    @SerializedName("totalResults") val count: Int = 0,
    @SerializedName("articles") val articles: List<APIArticle> = ArrayList(),
)
