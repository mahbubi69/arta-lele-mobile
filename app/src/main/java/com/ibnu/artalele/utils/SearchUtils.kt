package com.ibnu.artalele.utils

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SearchUtils {

    fun getSearchDebtResult(keyword: String): SimpleSQLiteQuery {
        val simpleSQLiteQuery = StringBuilder().append("SELECT * FROM debt ")
        simpleSQLiteQuery.append("JOIN debt_fts ON debt.name = debt_fts.name ")
        simpleSQLiteQuery.append("WHERE debt_fts MATCH $keyword")

        return SimpleSQLiteQuery(simpleSQLiteQuery.toString())
    }

}