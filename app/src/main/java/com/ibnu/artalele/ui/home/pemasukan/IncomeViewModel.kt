package com.ibnu.artalele.ui.home.pemasukan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(private val repository: PemasukanRepositoryImpl) : ViewModel() {

    fun getIncomeList(): LiveData<PagingData<IncomeEntity>> = repository.getListIncome()

    fun getNewestIncome(day: Int, month: String) = repository.get15Incomes(day, month)

    fun getIncomeTotal(month: String) : LiveData<Int> {
        val resultTotal = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.Main) {
            resultTotal.postValue(repository.getThisMonthIncomeTotal(month))
        }
        return resultTotal
    }

}