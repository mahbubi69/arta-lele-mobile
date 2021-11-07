package com.ibnu.artalele.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "category",
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val categoryId: Int = 0,
    @ColumnInfo(name = "category_name")
    val categoryName: String,
    @ColumnInfo(name = "category_group")
    val categoryGroup: String,
    @ColumnInfo(name = "category_image")
    val categoryImage: Int
)