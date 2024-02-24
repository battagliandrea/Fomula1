package it.battagliandrea.formula1.core.ui.navigation

import androidx.navigation.NavController
import it.battagliandrea.formula1.core.ui.activity.getActivity

fun NavController.popBackStackOrFinish() {
    if (!popBackStack()) {
        context.getActivity()?.finish()
    }
}
