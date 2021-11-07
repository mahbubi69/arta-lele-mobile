package com.ibnu.artalele.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.ibnu.artalele.data.entities.CategoryEntity

@Dao
interface TransactionDao {

    @Query("SELECT * FROM category WHERE category_id= :id")
    suspend fun getSingleCategory(id: Int) : CategoryEntity


}