package it.battagliandrea.formula1.feature.main.navigation

import com.bumble.appyx.utils.multiplatform.Parcelable
import com.bumble.appyx.utils.multiplatform.Parcelize

sealed class MainNavTarget : Parcelable {
    @Parcelize
    data object Schedule : MainNavTarget()

    @Parcelize
    data object Results : MainNavTarget()

    @Parcelize
    data object Standings : MainNavTarget()
}
