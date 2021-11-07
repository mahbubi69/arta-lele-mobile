package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ibnu.artalele.data.entities.SpendingEntity

@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpending(spending: SpendingEntity)

    @Query("SELECT * FROM spending WHERE day= :currentDay AND month=:month ORDER BY day DESC LIMIT 15")
    fun get15NewestSpending(currentDay: Int, month: String): PagingSource<Int, SpendingEntity>

    @Query("SELECT * FROM spending WHERE id= :id")
    suspend fun getSpendingById(id: Int): SpendingEntity

    @Query("SELECT total FROM spending WHERE month=:month")
    suspend fun getThisMonthSpendingTotal(month: String): List<Int>

    @Query("DELETE FROM spending WHERE id=:id")
    suspend fun deleteSpending(id: Int)
}