package com.ibnu.artalele.ui.hutang.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.ui.hutang.repo.BukuHutangRepositoryImpl
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailHutangViewModel(private val repository: BukuHutangRepositoryImpl) : ViewModel() {

    fun getDebtId(id: Int): LiveData<DebtEntity> {
        val result = MutableLiveData<DebtEntity>()
        viewModelScope.launch {
            try {
                result.postValue(repository.getDebtById(id))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return result
    }

    fun deleteDebt(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteDebt(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}