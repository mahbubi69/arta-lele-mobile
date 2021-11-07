package com.ibnu.artalele.ui.home.pengeluaran.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.SpendingEntity

interface PengeluaranRepository {

    fun getListSpending(): LiveData<PagingData<SpendingEntity>>
    fun get15Spending(day: Int, month: String): LiveData<PagingData<SpendingEntity>>

    suspend fun getThisMonthSpendingTotal(month: String): Int
    suspend fun getSpendingById(id: Int): SpendingEntity
    suspend fun deleteSpending(id: Int)
}