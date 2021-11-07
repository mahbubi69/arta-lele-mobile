package com.ibnu.artalele.ui.extra.category.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.CategoryEntity

interface CategoryRepository {
    fun getCategories(groupType: String): LiveData<PagingData<CategoryEntity>>
    suspend fun insertCategory(category: CategoryEntity)
    suspend fun deleteCategory(id: Int)
}