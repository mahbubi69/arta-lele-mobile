package com.ibnu.artalele.data.dao

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.ibnu.artalele.data.entities.DebtEntity

@Dao
interface DebtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebt(debt: DebtEntity)

    @RawQuery(observedEntities = [DebtEntity::class])
    fun getDebts(query: SupportSQLiteQuery): PagingSource<Int, DebtEntity>

    @Query ("SELECT * FROM debt WHERE id = :id")
    suspend fun getDebtById(id: Int): DebtEntity

    @Query("SELECT amount FROM debt")
    suspend fun getDebtAmount() : List<Int>

    @Update
    suspend fun updateDebt(debt: DebtEntity)

    @Query("DELETE FROM debt WHERE id = :id")
    suspend fun deleteDebt(id: Int)

}