package com.ibnu.artalele.ui.home.pengeluaran

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.ui.home.pengeluaran.repo.SpendingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpendingViewModel(private val repository: SpendingRepositoryImpl) : ViewModel() {

    fun getNewestSpending(day: Int, month: String) = repository.get15Spending(day, month)

    fun getThisMonthTotalSpending(month: String): LiveData<Int> {
        val result = MutableLiveData<Int>()
        viewModelScope.launch(Dispatchers.Main) {
            result.postValue(repository.getThisMonthSpendingTotal(month))
        }
        return result
    }

}