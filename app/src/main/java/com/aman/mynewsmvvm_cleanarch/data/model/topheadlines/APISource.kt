package com.aman.mynewsmvvm_cleanarch.data.model.topheadlines

import com.aman.mynewsmvvm_cleanarch.data.Source
import com.google.gson.annotations.SerializedName

data class APISource(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String = "",
)

fun APISource.asSourceEntity(): Source = Source(
    sourceId = id ?: "", name = name
)

fun APISource.asSourceIdEntity(sourceId: String): Source = Source(
    sourceId = sourceId, name = name
)
