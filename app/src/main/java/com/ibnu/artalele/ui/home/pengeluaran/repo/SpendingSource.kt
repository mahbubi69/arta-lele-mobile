package com.ibnu.artalele.ui.home.pengeluaran.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ibnu.artalele.data.dao.SpendingDao

class SpendingSource(private val dao: SpendingDao) {

    fun get15NewestSpending(day: Int, month: String) = Pager(
        config = PagingConfig(15)
    ){
        dao.get15NewestSpending(day, month)
    }.liveData

    suspend fun getSpendingById(id: Int) = dao.getSpendingById(id)

    suspend fun deleteSpending(id: Int) = dao.deleteSpending(id)

    suspend fun getThisMonthSpendingTotal(month: String) = dao.getThisMonthSpendingTotal(month)
}