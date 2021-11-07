package com.ibnu.artalele.utils

import android.content.Context
import android.content.SharedPreferences
import com.ibnu.artalele.R
import com.ibnu.artalele.utils.ConstValue.KEY_BASE_PRICE
import com.ibnu.artalele.utils.ConstValue.KEY_TRANSACTION_CATEGORY
import com.ibnu.artalele.utils.ConstValue.KEY_TRANSACTION_DATE
import com.ibnu.artalele.utils.ConstValue.KEY_TRANSACTION_NOTE
import com.ibnu.artalele.utils.ConstValue.KEY_TRANSACTION_RESULT
import com.ibnu.artalele.utils.ConstValue.KEY_TRANSACTION_WEIGHT
import com.ibnu.artalele.utils.ConstValue.PREFS_NAME

class SharedPreferencesManager(context: Context) {
    private var prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = prefs.edit()

    private val defaultDesc = context.resources.getString(R.string.keperluan)

    fun setBasePrice(price: Int) {
        editor.putInt(KEY_BASE_PRICE, price)
        editor.apply()
    }

    fun setTransactionDescription(desc: String){
        editor.putString(KEY_TRANSACTION_NOTE, desc)
        editor.apply()
    }

    fun setTransactionDate(date: String) {
        editor.putString(KEY_TRANSACTION_DATE, date)
        editor.apply()
    }

    fun setTransactionCategory(id: Int){
        editor.putInt(KEY_TRANSACTION_CATEGORY, id)
        editor.apply()
    }

    fun setTransactionResult(value: Int){
        editor.putInt(KEY_TRANSACTION_RESULT, value)
        editor.apply()
    }

    fun setTransactionWeight(value: Long){
        editor.putLong(KEY_TRANSACTION_WEIGHT, value)
        editor.apply()
    }

    fun resetTransactionSegment() {
        editor.remove(KEY_TRANSACTION_CATEGORY)
        editor.remove(KEY_TRANSACTION_NOTE)
        editor.remove(KEY_TRANSACTION_RESULT)
        editor.remove(KEY_TRANSACTION_WEIGHT)
        editor.apply()
    }

    fun deleteWeight(){
        editor.remove(KEY_TRANSACTION_WEIGHT)
        editor.apply()
    }

    fun deleteResult(){
        editor.remove(KEY_TRANSACTION_RESULT)
        editor.apply()
    }

    val getPrice = prefs.getInt(KEY_BASE_PRICE, 0)

    val getTransCategory = prefs.getInt(KEY_TRANSACTION_CATEGORY, 0)

    val getTransDesc = prefs.getString(KEY_TRANSACTION_NOTE, defaultDesc)

    val getTransResult = prefs.getInt(KEY_TRANSACTION_RESULT, 0)

    val getTransWeight = prefs.getLong(KEY_TRANSACTION_WEIGHT, 0)

    val getTransDate = prefs.getString(KEY_TRANSACTION_DATE, "Hari ini")

}