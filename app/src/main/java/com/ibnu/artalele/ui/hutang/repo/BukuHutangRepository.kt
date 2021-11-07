package com.ibnu.artalele.ui.hutang.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.DebtEntity

interface BukuHutangRepository{
     fun getDebts(type: String): LiveData<PagingData<DebtEntity>>
     fun getDebtsSearch(keyword: String): LiveData<PagingData<DebtEntity>>
     suspend fun getTotalAmount(): Int
     suspend fun getDebtById(id: Int): DebtEntity
     suspend fun deleteDebt(id: Int)
}