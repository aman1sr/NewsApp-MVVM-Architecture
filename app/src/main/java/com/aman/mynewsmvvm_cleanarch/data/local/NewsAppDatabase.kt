package com.aman.mynewsmvvm_cleanarch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aman.mynewsmvvm_cleanarch.data.local.dao.TopHeadLineDao
import com.aman.mynewsmvvm_cleanarch.data.local.entity.Article
import com.aman.mynewsmvvm_cleanarch.data.local.entity.Country
import com.aman.mynewsmvvm_cleanarch.data.local.entity.LanguageEntity
import com.aman.mynewsmvvm_cleanarch.data.local.entity.NewsSource


@Database(
    entities = [Article::class, NewsSource::class, LanguageEntity::class, Country::class],
    version = 1,
    exportSchema = false
)
abstract class NewsAppDatabase : RoomDatabase() {
    abstract fun topHeadLineDao(): TopHeadLineDao

}