package com.ibnu.artalele.utils

import com.ibnu.artalele.R
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.ConstValue.SPENDING

class DataGenerator {

    companion object {
        fun populateCategory(): List<CategoryEntity> {
            val categories = ArrayList<CategoryEntity>()

            categories.add(
                CategoryEntity(
                    categoryId = 1,
                    categoryName = "Penjualan Lele",
                    categoryGroup = INCOME,
                    categoryImage = R.drawable.ic_buku_hutang
                )
            )

            categories.add(
                CategoryEntity(
                    categoryId = 2,
                    categoryName = "Bensin",
                    categoryGroup = SPENDING,
                    categoryImage = R.drawable.ic_buku_hutang
                )
            )

            categories.add(
                CategoryEntity(
                    categoryId = 3,
                    categoryName = "Perlengkapan",
                    categoryGroup = SPENDING,
                    categoryImage = R.drawable.ic_buku_hutang
                )
            )

            return categories
        }
    }

}