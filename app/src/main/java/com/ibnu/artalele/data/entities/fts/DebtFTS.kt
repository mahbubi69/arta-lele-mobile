package com.ibnu.artalele.data.entities.fts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import com.ibnu.artalele.data.entities.DebtEntity

@Entity(tableName = "debt_fts")
@Fts4(contentEntity = DebtEntity::class)
data class DebtFTS(
    val name: String,
    @ColumnInfo(name = "start_date")
    val startDate: String,
)