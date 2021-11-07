package com.ibnu.artalele.ui.home.tambah

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.SharedPreferencesManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TambahTransaksiViewModel(private val transactionRepository: TransactionRepositoryImpl) :
    ViewModel() {

    fun getCategory(id: Int): LiveData<CategoryEntity> {
        val resultCategory = MutableLiveData<CategoryEntity>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                resultCategory.postValue(transactionRepository.getCategoryById(id))
            }
        }
        return resultCategory
    }

    fun getDescription(context: Context): LiveData<String> {
        val resultDescription = MutableLiveData<String>()
        val desc = SharedPreferencesManager(context).getTransDesc
        resultDescription.postValue(desc ?: "Pilih Kategori")
        return resultDescription
    }

    fun insertIncomeTransaction(income: IncomeEntity) {
        viewModelScope.launch {
            transactionRepository.insertIncome(income)
        }
    }

    fun insertSpendingTransaction(spending: SpendingEntity) {
        viewModelScope.launch {
            transactionRepository.insertSpending(spending)
        }
    }
}