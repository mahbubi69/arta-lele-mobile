package com.ibnu.artalele.ui.hutang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.ui.hutang.repo.BukuHutangRepositoryImpl
import kotlinx.coroutines.launch

class BukuHutangViewModel(private val repository: BukuHutangRepositoryImpl) : ViewModel() {

    fun getDebts(type: String): LiveData<PagingData<DebtEntity>> = repository.getDebts(type)

    fun getSearchDebt(keyword: String): LiveData<PagingData<DebtEntity>> =
        repository.getDebtsSearch(keyword)

    fun getTotalAmountDebt(): LiveData<Int> {
        val totalAmountLiveData = MutableLiveData<Int>()
        viewModelScope.launch {
            totalAmountLiveData.postValue(repository.getTotalAmount())
        }
        return totalAmountLiveData
    }

}