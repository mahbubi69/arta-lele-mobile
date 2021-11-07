package com.ibnu.artalele.ui.extra.category.tambah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.ui.extra.category.repo.CategoryRepositoryImpl
import kotlinx.coroutines.launch

class AddCategoryViewModel(private val categoryRepositoryImpl: CategoryRepositoryImpl) : ViewModel() {

    fun addCategory(category: CategoryEntity) {
        viewModelScope.launch {
            categoryRepositoryImpl.insertCategory(category)
        }
    }

}