package com.ibnu.artalele.ui.home.pemasukan.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.IncomeEntity

interface PemasukanRepository {
    fun getListIncome(): LiveData<PagingData<IncomeEntity>>
    fun get15Incomes(day: Int, month: String): LiveData<PagingData<IncomeEntity>>

    suspend fun getThisMonthIncomeTotal(month: String): Int
    suspend fun getIncomeById(id: Int): IncomeEntity
    suspend fun deleteIncome(id: Int)
}