package com.ibnu.artalele.ui.home.pemasukan.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ibnu.artalele.data.dao.IncomeDao

class IncomeSource(private val incomeDao: IncomeDao) {

    fun getIncomes() = Pager(
        config = PagingConfig(10)
    ) {
        incomeDao.getAllIncome()
    }.liveData

    fun get15NewestIncomes(day: Int, month: String) = Pager(
        config = PagingConfig(15)
    ) {
        incomeDao.getOnly15NewestIncomes(day, month)
    }.liveData

    suspend fun getDebtById(id: Int) = incomeDao.getIncomeById(id)

    suspend fun getThisMonthIncomeTotal(month: String) = incomeDao.getThisMonthIncomeTotal(month)

    suspend fun deleteIncome(id: Int) = incomeDao.deleteIncome(id)


}