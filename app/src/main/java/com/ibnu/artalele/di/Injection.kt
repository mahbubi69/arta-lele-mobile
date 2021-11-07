package com.ibnu.artalele.di

import android.content.Context
import com.ibnu.artalele.data.dao.*
import com.ibnu.artalele.data.database.ArtaDatabase
import com.ibnu.artalele.ui.extra.category.repo.CategoryDataSource
import com.ibnu.artalele.ui.extra.category.repo.CategoryRepositoryImpl
import com.ibnu.artalele.ui.home.pemasukan.repo.PemasukanRepositoryImpl
import com.ibnu.artalele.ui.home.pemasukan.source.IncomeSource
import com.ibnu.artalele.ui.home.pengeluaran.repo.SpendingRepositoryImpl
import com.ibnu.artalele.ui.home.pengeluaran.repo.SpendingSource
import com.ibnu.artalele.ui.home.tambah.TransactionRepositoryImpl
import com.ibnu.artalele.ui.hutang.repo.BukuHutangRepositoryImpl
import com.ibnu.artalele.ui.hutang.repo.TambahBukuHutangRepository
import com.ibnu.artalele.ui.hutang.source.HutangDataSource

object Injection {

    private fun provideDatabase(context: Context): ArtaDatabase = ArtaDatabase.getDatabase(context)


    //    spending Injection
    private fun provideSpendingDao(context: Context): SpendingDao =
        provideDatabase(context).getSpendingDao()

    private fun provideSpendingSource(context: Context): SpendingSource =
        SpendingSource(provideSpendingDao(context))

    fun provideSpendingRepository(context: Context): SpendingRepositoryImpl =
        SpendingRepositoryImpl(
            provideSpendingSource(context)
        )


    //    income Injection
    private fun provideIncomeDao(context: Context): IncomeDao =
        provideDatabase(context).getIncomeDao()

    private fun provideIncomeSource(context: Context): IncomeSource =
        IncomeSource(provideIncomeDao(context))

    fun provideIncomeRepository(context: Context): PemasukanRepositoryImpl =
        PemasukanRepositoryImpl(
            provideIncomeSource(context)
        )


    //    debt Injection
    private fun provideDebtDao(context: Context): DebtDao =
        provideDatabase(context).getDebtDao()

    private fun provideHutangDataSource(context: Context): HutangDataSource = HutangDataSource(
        provideDebtDao(context)
    )

    fun provideTambahBukuHutangRepository(context: Context): TambahBukuHutangRepository =
        TambahBukuHutangRepository(
            provideDebtDao(context)
        )

    fun provideBukuHutangRepository(context: Context): BukuHutangRepositoryImpl =
        BukuHutangRepositoryImpl(
            provideHutangDataSource(context)
        )


    //    Category Injection
    private fun provideCategoryDao(context: Context): CategoryDao =
        provideDatabase(context).getCategoryDao()

    private fun provideCategoryDataSource(context: Context): CategoryDataSource =
        CategoryDataSource(provideCategoryDao(context))

    fun provideCategoryRepository(context: Context): CategoryRepositoryImpl =
        CategoryRepositoryImpl(
            provideCategoryDataSource(context)
        )


    //    Transaction Injection
    fun provideTransactionRepository(context: Context): TransactionRepositoryImpl =
        TransactionRepositoryImpl(
            provideCategoryDao(context), provideIncomeDao(context), provideSpendingDao(context)
        )

}