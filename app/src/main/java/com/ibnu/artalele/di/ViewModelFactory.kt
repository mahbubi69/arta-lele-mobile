package com.ibnu.artalele.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibnu.artalele.ui.extra.category.IncomeCategoryViewModel
import com.ibnu.artalele.ui.extra.category.SpendingCategoryViewModel
import com.ibnu.artalele.ui.extra.category.repo.CategoryRepositoryImpl
import com.ibnu.artalele.ui.extra.category.tambah.AddCategoryViewModel
import com.ibnu.artalele.ui.home.pemasukan.IncomeViewModel
import com.ibnu.artalele.ui.home.pemasukan.detail.DetailIncomeViewModel
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl
import com.ibnu.artalele.ui.home.pengeluaran.SpendingViewModel
import com.ibnu.artalele.ui.home.pengeluaran.detail.DetailSpendingViewModel
import com.ibnu.artalele.ui.home.pengeluaran.repo.SpendingRepositoryImpl
import com.ibnu.artalele.ui.home.tambah.TambahTransaksiViewModel
import com.ibnu.artalele.ui.home.tambah.TransactionRepositoryImpl
import com.ibnu.artalele.ui.hutang.BukuHutangViewModel
import com.ibnu.artalele.ui.hutang.detail.DetailHutangViewModel
import com.ibnu.artalele.ui.hutang.repo.BukuHutangRepositoryImpl
import com.ibnu.artalele.ui.hutang.repo.TambahBukuHutangRepository
import com.ibnu.artalele.ui.hutang.tambah.TambahHutangViewModel

class ViewModelFactory(
    private val tambahBukuHutangRepository: TambahBukuHutangRepository,
    private val bukuHutangRepository: BukuHutangRepositoryImpl,
    private val pemasukanRepositoryImpl: PemasukanRepositoryImpl,
    private val pengeluaranRepositoryImpl: SpendingRepositoryImpl,
    private val categoryRepositoryImpl: CategoryRepositoryImpl,
    private val transactionRepository: TransactionRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            (instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideTambahBukuHutangRepository(context),
                    Injection.provideBukuHutangRepository(context),
                    Injection.provideIncomeRepository(context),
                    Injection.provideSpendingRepository(context),
                    Injection.provideCategoryRepository(context),
                    Injection.provideTransactionRepository(context)
                )
            })
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TambahHutangViewModel::class.java) -> {
                TambahHutangViewModel(tambahBukuHutangRepository) as T
            }
            modelClass.isAssignableFrom(BukuHutangViewModel::class.java) -> {
                BukuHutangViewModel(bukuHutangRepository) as T
            }
            modelClass.isAssignableFrom(DetailHutangViewModel::class.java) -> {
                DetailHutangViewModel(bukuHutangRepository) as T
            }
            modelClass.isAssignableFrom(IncomeViewModel::class.java) -> {
                IncomeViewModel(pemasukanRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(DetailIncomeViewModel::class.java) -> {
                DetailIncomeViewModel(pemasukanRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(SpendingViewModel::class.java) -> {
                SpendingViewModel(pengeluaranRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(DetailSpendingViewModel::class.java) -> {
                DetailSpendingViewModel(pengeluaranRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(IncomeCategoryViewModel::class.java) -> {
                IncomeCategoryViewModel(categoryRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(SpendingCategoryViewModel::class.java) -> {
                SpendingCategoryViewModel(categoryRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(AddCategoryViewModel::class.java) -> {
                AddCategoryViewModel(categoryRepositoryImpl) as T
            }
            modelClass.isAssignableFrom(TambahTransaksiViewModel::class.java) -> {
                TambahTransaksiViewModel(transactionRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}