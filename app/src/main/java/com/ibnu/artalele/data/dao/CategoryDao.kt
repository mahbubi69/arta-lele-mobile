package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.ibnu.artalele.data.entities.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert
    fun insertAllCategories(categories: List<CategoryEntity>)

    @RawQuery(observedEntities = [CategoryEntity::class])
    fun getCategories(query: SupportSQLiteQuery): PagingSource<Int, CategoryEntity>

    @Query("SELECT * FROM category WHERE category_id= :id")
    suspend fun getSingleCategory(id: Int) : CategoryEntity

    @Query("DELETE FROM category WHERE category_id = :id")
    suspend fun deleteDebt(id: Int)

}