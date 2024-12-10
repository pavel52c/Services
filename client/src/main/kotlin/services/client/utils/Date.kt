package services.client.utils

import java.text.SimpleDateFormat
import java.util.*

class Date {
    fun getTomorrowDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        val tomorrow = calendar.time

        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(tomorrow)
    }
}