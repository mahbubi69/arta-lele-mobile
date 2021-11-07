package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ibnu.artalele.data.entities.IncomeEntity

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income: IncomeEntity)

    @Query("SELECT * FROM income ORDER BY id ASC")
    fun getAllIncome(): PagingSource<Int, IncomeEntity>

    @Query("SELECT * FROM income WHERE day= :currentDay AND month=:month ORDER BY day DESC LIMIT 15")
    fun getOnly15NewestIncomes(currentDay: Int, month: String): PagingSource<Int, IncomeEntity>

    @Query("SELECT total FROM income WHERE month=:month")
    suspend fun getThisMonthIncomeTotal(month: String): List<Int>

    @Query("SELECT * FROM income WHERE id= :id")
    suspend fun getIncomeById(id: Int): IncomeEntity

    @Update
    suspend fun updateIncome(income: IncomeEntity)

    @Query("DELETE FROM income WHERE id = :id")
    suspend fun deleteIncome(id: Int)

}