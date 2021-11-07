package com.ibnu.artalele.ui.hutang.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ibnu.artalele.data.dao.DebtDao
import com.ibnu.artalele.utils.SearchUtils
import com.ibnu.artalele.utils.type.TypeUtils

class HutangDataSource(private val debtDao: DebtDao) {

    fun getDebts(type: String) = Pager(
        config = PagingConfig(10)
    ) {
        val query = TypeUtils.getDebtByType(type)
        debtDao.getDebts(query)
    }.liveData

    suspend fun getDebtById(id: Int) = debtDao.getDebtById(id)

    suspend fun deleteDebt(id: Int) = debtDao.deleteDebt(id)

    fun getSearchDebts(keyword: String) = Pager(
        config = PagingConfig(10, enablePlaceholders = true)
    ) {
        val query = SearchUtils.getSearchDebtResult(keyword)
        debtDao.getDebts(query)
    }.liveData

    suspend fun getAmount() = debtDao.getDebtAmount()

}