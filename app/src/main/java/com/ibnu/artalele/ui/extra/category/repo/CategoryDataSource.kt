package com.ibnu.artalele.ui.extra.category.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ibnu.artalele.data.dao.CategoryDao
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.utils.type.TypeUtils

class CategoryDataSource(private val categoryDao: CategoryDao) {

    fun getCategories(groupType: String) = Pager(
        PagingConfig(10)
    ) {
        val query = TypeUtils.getCategoryByGroup(groupType)
        categoryDao.getCategories(query)
    }.liveData

    suspend fun insertCategory(category: CategoryEntity) = categoryDao.insertCategory(category)

    suspend fun deleteCategory(id: Int) = categoryDao.deleteDebt(id)

}