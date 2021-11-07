package com.ibnu.artalele.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spending")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val day: Int,
    val month: String,
    val year: Int,
    val total: Int,
    val description: String?,
    @Embedded(prefix = "category")
    val category: CategoryEntity
)
