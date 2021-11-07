package com.ibnu.artalele.ui.home.pemasukan.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl
import kotlinx.coroutines.launch

class DetailIncomeViewModel(private val repository: PemasukanRepositoryImpl) : ViewModel() {

    fun getIncomeDetail(id: Int): LiveData<IncomeEntity> {
        val resultIncomeDetail = MutableLiveData<IncomeEntity>()
        viewModelScope.launch {
            resultIncomeDetail.postValue(repository.getIncomeById(id))
        }
        return resultIncomeDetail
    }

    fun deleteIncome(id: Int){
        viewModelScope.launch {
            repository.deleteIncome(id)
        }
    }

}