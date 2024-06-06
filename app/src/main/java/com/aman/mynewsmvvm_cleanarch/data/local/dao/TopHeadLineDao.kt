package com.aman.mynewsmvvm_cleanarch.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aman.mynewsmvvm_cleanarch.data.local.entity.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface TopHeadLineDao {
    /*
    * @Transaction annotation is used to indicate that the function should be executed within a database transaction.
    * This means that all operations within the function are executed atomically:
    * if one operation fails, all operations are rolled back, ensuring data consistency.
    * */
    @Transaction
    @Query("SELECT * FROM TopHeadlinesArticle WHERE country =:country")
    fun getAllTopHeadlinesArticle(country: String): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<Article>): List<Long>

    @Query("DELETE  FROM TopHeadlinesArticle WHERE country =:country")
    fun clearTopHeadLinesArticles(country: String)

    @Transaction
    suspend fun insertAndDeleteTopHeadLineArticles(
        country: String, articles: List<Article>
    ): List<Long> {
        clearTopHeadLinesArticles(country)
        return insertArticles(articles)
    }


}