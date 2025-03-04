package com.codepath.articlesearch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article_table")
    fun getAll(): Flow<List<ArticleEntity>>

    @Insert
    fun insertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM article_table")
    fun deleteAll()
}

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<FoodEntity>>

    @Insert
    fun insertAll(foods: List<FoodEntity>)

    @Query("DELETE FROM food_table")
    fun deleteAll()
}