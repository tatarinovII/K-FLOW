package my.tatarinov.kflow.presentation.utils

import kotlin.time.Clock
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

object DateFormatter {

    fun headerDate(date: LocalDate = today()): String {
        val day = when (date.dayOfWeek) {
            DayOfWeek.MONDAY -> "Понедельник"
            DayOfWeek.TUESDAY -> "Вторник"
            DayOfWeek.WEDNESDAY -> "Среда"
            DayOfWeek.THURSDAY -> "Четверг"
            DayOfWeek.FRIDAY -> "Пятница"
            DayOfWeek.SATURDAY -> "Суббота"
            DayOfWeek.SUNDAY -> "Воскресенье"
        }
        val month = when (date.month) {
            Month.JANUARY   -> "января"
            Month.FEBRUARY  -> "февраля"
            Month.MARCH     -> "марта"
            Month.APRIL     -> "апреля"
            Month.MAY       -> "мая"
            Month.JUNE      -> "июня"
            Month.JULY      -> "июля"
            Month.AUGUST    -> "августа"
            Month.SEPTEMBER -> "сентября"
            Month.OCTOBER   -> "октября"
            Month.NOVEMBER  -> "ноября"
            Month.DECEMBER  -> "декабря"
        }
        return "$day, ${date.dayOfMonth} $month"
    }

    private fun today(): LocalDate =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
}