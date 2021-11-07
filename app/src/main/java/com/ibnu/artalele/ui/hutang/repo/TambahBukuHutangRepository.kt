package com.ibnu.artalele.ui.hutang.repo

import com.ibnu.artalele.data.dao.DebtDao
import com.ibnu.artalele.data.entities.DebtEntity

class TambahBukuHutangRepository(private val debtDao: DebtDao) {

    suspend fun insertDebt(debt: DebtEntity) {
        try {
            debtDao.insertDebt(debt)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}