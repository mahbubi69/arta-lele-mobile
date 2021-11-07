package com.ibnu.artalele.ui.home.pengeluaran.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.ui.home.pengeluaran.repo.SpendingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailSpendingViewModel(private val repo: SpendingRepositoryImpl) : ViewModel() {


    fun getDetailSpending(id: Int): LiveData<SpendingEntity> {
        val result = MutableLiveData<SpendingEntity>()
        viewModelScope.launch {
            result.postValue(repo.getSpendingById(id))
        }
        return result
    }

    fun deleteSpending(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteSpending(id = id)
        }
    }

}