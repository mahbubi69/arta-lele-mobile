package com.ibnu.artalele.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debt")
data class DebtEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val amount: Int,
    @ColumnInfo(name = "start_date")
    val startDate: String,
    @ColumnInfo(name = "due_date")
    val dueDate: String,
    val description: String?
)
