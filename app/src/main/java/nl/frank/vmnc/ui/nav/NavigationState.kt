package nl.frank.vmnc.ui.nav

import java.util.UUID

sealed class NavigationState {

    /**
     * @param id is used so that multiple instances of the same route will trigger multiple navigation calls.
     */

    object Idle : NavigationState()

    data class NavigateToRoute(val route: String, val id: String = UUID.randomUUID().toString()) :
        NavigationState()

    /**
     * @param staticRoute is the static route to pop to, without parameter replacements.
     */
    data class PopToRoute(val staticRoute: String, val id: String = UUID.randomUUID().toString()) :
        NavigationState()
}