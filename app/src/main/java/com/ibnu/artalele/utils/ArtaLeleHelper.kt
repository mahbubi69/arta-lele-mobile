package com.ibnu.artalele.utils

import android.app.DatePickerDialog
import android.content.Context
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ArtaLeleHelper {
    companion object {
        fun convertStringToNumberOnly(string: String): Int {
            val rawNumber = string.filter { it.isDigit() }
            return Integer.parseInt(rawNumber)
        }

        fun convertStringToLong(value: String): Long {
            val rawLong = value.filter { it.isDigit() }
            return rawLong.toLong()
        }

        fun addRupiahToAmount(amount: Int): String {
            val formattedAmount = NumberFormat.getIntegerInstance().format(amount)
            return "Rp $formattedAmount"
        }

        fun addRupiahToThousandAmountFromString(amount: String): String {
            var rawAmount = ""
            rawAmount = if (amount.contains(".")) {
                amount.substring(0, amount.indexOf("."))
            } else {
                amount
            }
            val formattedAmount = NumberFormat.getIntegerInstance().format(rawAmount.toInt())
            return "Rp $formattedAmount"
        }

        fun addKgToWeight(amount: Long): String {
            return "$amount Kg"
        }

        fun getTodayDate(): String {
            val date = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.ROOT)

            return dateFormat.format(date)
        }

        fun getDatePickerDialog(
            context: Context,
            dateOnSetlistener: DatePickerDialog.OnDateSetListener
        ): DatePickerDialog {
            val cal = Calendar.getInstance()

            return DatePickerDialog(
                context,
                dateOnSetlistener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
        }

        fun getCurrentMonth(): String {
            val calendar = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("MMMM", Locale.ROOT)
            return dateFormat.format(calendar)
        }
        fun getCurrentDay(): Int {
            val calendar = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd", Locale.ROOT)
            return Integer.parseInt(dateFormat.format(calendar))
        }

        fun getCurrentYear(): Int {
            val calendar = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("yyyy", Locale.ROOT)
            return Integer.parseInt(dateFormat.format(calendar))
        }

        fun formatSelectedDate(calendar: Calendar): String {
            val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return format.format(calendar.time)
        }

        fun getMonthFromDate(date: String): String {
            return date.filter { it.isLetter() }
        }

        fun getDayFromDate(date: String): Int {
            return Integer.parseInt(date.filter { it.isDigit() }.substring(0, 2))
        }

        fun getYearFromDate(date: String): Int{
            val digit = date.filter { it.isDigit() }
            val year = digit.substring((digit.length-4), digit.length)

            return Integer.parseInt(year)
        }

    }
}