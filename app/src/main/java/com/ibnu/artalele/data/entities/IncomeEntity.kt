package com.ibnu.artalele.data.entities

import androidx.room.*

@Entity(
    tableName = "income"
)

data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: Int,
    val month: String,
    val year: Int,
    val weight: String,
    val total: Int,
    val description: String?,
    @Embedded(prefix = "category")
    val category: CategoryEntity
)
