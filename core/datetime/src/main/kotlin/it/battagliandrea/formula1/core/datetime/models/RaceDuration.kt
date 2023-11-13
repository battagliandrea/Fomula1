package it.battagliandrea.formula1.core.datetime.models

import java.util.concurrent.TimeUnit

/**
 * Represents the duration of a race in hours, minutes, seconds, and milliseconds.
 *
 * @param hours The duration in hours.
 * @param minutes The duration in minutes.
 * @param seconds The duration in seconds.
 * @param milliseconds The duration in milliseconds.
 */
data class RaceDuration(
    val hours: Long,
    val minutes: Long,
    val seconds: Long,
    val milliseconds: Long,
) {
    override fun toString(): String =
        "$hours,$minutes,$seconds.$milliseconds"

    /**
     * Creates a RaceDuration object from a duration in milliseconds.
     *
     * @param milliseconds The duration in milliseconds to convert to a RaceDuration.
     */
    companion object {
        private val default = RaceDuration(0, 0, 0, 0)

        fun fromMillisecond(milliseconds: Long?): RaceDuration {
            if (milliseconds == null) return default

            var input = milliseconds
            val hours = TimeUnit.MILLISECONDS.toHours(input)
            input -= TimeUnit.HOURS.toMillis(hours)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(input)
            input -= TimeUnit.MINUTES.toMillis(minutes)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(input)
            input -= TimeUnit.SECONDS.toMillis(seconds)

            return RaceDuration(hours, minutes, seconds, milliseconds)
        }
    }
}
