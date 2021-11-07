package com.ibnu.artalele.utils.type

import androidx.sqlite.db.SimpleSQLiteQuery
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.ConstValue.SPENDING
import java.lang.StringBuilder

object TypeUtils{

    const val NEW_DEBT = "mew debt"
    const val OLD_DEBT = "old debt"

    fun getDebtByType(filterType: String): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM debt ")
        when (filterType) {
            NEW_DEBT -> {
                simpleSQLiteQuery.append("ORDER BY start_date DESC")
            }
            OLD_DEBT -> {
                simpleSQLiteQuery.append("ORDER BY start_date ASC")
            }
        }
        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

    fun getCategoryByGroup(groupType: String): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM category ")
        when (groupType) {
            INCOME -> {
                simpleSQLiteQuery.append("WHERE category_group ='$INCOME'")
            }
            SPENDING -> {
                simpleSQLiteQuery.append("WHERE category_group ='$SPENDING'")
            }
        }
        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

}
