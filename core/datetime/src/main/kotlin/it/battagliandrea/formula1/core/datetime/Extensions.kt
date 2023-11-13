package it.battagliandrea.formula1.core.datetime

import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalTime

/**
 * Converts a string representing a race time to a LocalTime value.
 */
fun String.toRaceTime(): LocalTime =
    this.replace("Z", "").toLocalTime()
