package com.aman.mynewsmvvm_cleanarch.data.model.topheadlines

import com.aman.mynewsmvvm_cleanarch.data.local.entity.Article
import com.google.gson.annotations.SerializedName

data class APIArticle(
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String? = "",
    @SerializedName("url") val url: String = "",
    @SerializedName("urlToImage") val imageUrl: String? = "",
    @SerializedName("source") val source: APISource
)
/*
*  such an extension function is to transform data models between different layers of an application. In this case,
*   it helps in converting network data models into entities that can be stored in a database or used within the application logic.
* */
fun APIArticle.asEntity(country: String) = Article(
    title = title,
    description = description ?: "",
    url = url,
    imageUrl = imageUrl ?: "",
    country = country,
    source = source.asSourceEntity()
)

fun APIArticle.asSourceIdEntity(sourceId: String) = Article(
    title = title,
    description = description ?: "",
    url = url,
    imageUrl = imageUrl ?: "",
    source = source.asSourceIdEntity(sourceId)
)

fun APIArticle.asLanguageEntity(language: String) = Article(
    title = title,
    description = description ?: "",
    url = url,
    imageUrl = imageUrl ?: "",
    language = language,
    source = source.asSourceEntity()
)
