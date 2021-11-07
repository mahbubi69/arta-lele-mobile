package com.ibnu.artalele.ui.hutang.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.ui.hutang.source.HutangDataSource

class BukuHutangRepositoryImpl(private val debtDataSource: HutangDataSource) :
    BukuHutangRepository {

    override fun getDebts(type: String): LiveData<PagingData<DebtEntity>> =
        debtDataSource.getDebts(type)

    override fun getDebtsSearch(keyword: String): LiveData<PagingData<DebtEntity>> =
        debtDataSource.getSearchDebts(keyword)

    override suspend fun getTotalAmount(): Int {
        val listAmount = debtDataSource.getAmount()
        var resultAmount = 0

        for (i in listAmount) {
            resultAmount += i
        }
        return resultAmount
    }

    override suspend fun getDebtById(id: Int): DebtEntity = debtDataSource.getDebtById(id)

    override suspend fun deleteDebt(id: Int) {
        debtDataSource.deleteDebt(id)
    }

}