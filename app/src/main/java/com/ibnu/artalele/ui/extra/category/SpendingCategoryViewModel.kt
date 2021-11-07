package com.ibnu.artalele.ui.extra.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.ui.extra.category.repo.CategoryRepositoryImpl
import com.ibnu.artalele.utils.ConstValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SpendingCategoryViewModel(private val repository: CategoryRepositoryImpl) : ViewModel() {

    fun getCategories(): LiveData<PagingData<CategoryEntity>> =
        repository.getCategories(ConstValue.SPENDING)

}