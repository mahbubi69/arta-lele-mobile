package com.ibnu.artalele.ui.home.tambah

import com.ibnu.artalele.data.dao.CategoryDao
import com.ibnu.artalele.data.dao.IncomeDao
import com.ibnu.artalele.data.dao.SpendingDao
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionRepositoryImpl(
    private val categoryDao: CategoryDao,
    private val incomeDao: IncomeDao,
    private val spendingDao: SpendingDao
) : TransactionRepository {

    override suspend fun getCategoryById(id: Int): CategoryEntity =
        categoryDao.getSingleCategory(id)

    override suspend fun insertIncome(incomeEntity: IncomeEntity) {
        withContext(Dispatchers.IO){
            incomeDao.insertIncome(incomeEntity)
        }
    }

    override suspend fun insertSpending(spendingEntity: SpendingEntity) {
        withContext(Dispatchers.IO){
            spendingDao.insertSpending(spendingEntity)
        }
    }

}