package com.ibnu.artalele.ui.hutang.tambah

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.ui.hutang.repo.TambahBukuHutangRepository
import kotlinx.coroutines.*

class TambahHutangViewModel(
    private val tambahBukuHutangRepository: TambahBukuHutangRepository
) : ViewModel() {

    fun insertDebt(debt: DebtEntity) {
        viewModelScope.launch() {
            withContext(Dispatchers.IO){
                tambahBukuHutangRepository.insertDebt(debt)
            }
        }
    }

}