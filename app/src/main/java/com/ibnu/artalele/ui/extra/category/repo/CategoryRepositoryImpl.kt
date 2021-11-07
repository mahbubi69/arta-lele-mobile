package com.ibnu.artalele.ui.extra.category.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.CategoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(private val categoryDataSource: CategoryDataSource) :
    CategoryRepository {
    override fun getCategories(groupType: String): LiveData<PagingData<CategoryEntity>> =
        categoryDataSource.getCategories(groupType)

    override suspend fun insertCategory(category: CategoryEntity) {
        withContext(Dispatchers.IO){
            categoryDataSource.insertCategory(category)
        }
    }

    override suspend fun deleteCategory(id: Int) = categoryDataSource.deleteCategory(id)
}