package com.ibnu.artalele.ui.home.pengeluaran.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.SpendingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpendingRepositoryImpl(private val source: SpendingSource) : PengeluaranRepository {

    override fun getListSpending(): LiveData<PagingData<SpendingEntity>> {

        TODO("Not yet implemented")
    }

    override fun get15Spending(day: Int, month: String): LiveData<PagingData<SpendingEntity>> =
        source.get15NewestSpending(day, month)

    override suspend fun getThisMonthSpendingTotal(month: String): Int {
        var result = 0
        withContext(Dispatchers.Default){
            for (i in source.getThisMonthSpendingTotal(month)){
                result += i
            }
        }
        return result
    }

    override suspend fun getSpendingById(id: Int): SpendingEntity = source.getSpendingById(id)

    override suspend fun deleteSpending(id: Int) = source.deleteSpending(id)
}