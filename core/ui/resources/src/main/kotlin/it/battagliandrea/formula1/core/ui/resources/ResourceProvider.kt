package it.battagliandrea.formula1.core.ui.resources

object ResourceProvider {

    fun getDriverAvatarByName(driverId: String) = when (driverId) {
        "max_verstappen" -> R.drawable.verstappen
        "perez" -> R.drawable.perez
        "leclerc" -> R.drawable.leclerc
        else -> 0
    }
}
