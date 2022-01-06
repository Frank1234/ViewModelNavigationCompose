package nl.frank.vmnc.ui.nav.model

sealed class NavigationState {

    // TODO id needed? Don't think so, because we're not using multiple states in our Flow at once anyways?

    class Idle : NavigationState()

    data class NavigateToRoute(val route: String) : NavigationState()

    /**
     * @param staticRoute is the static route to pop to, without parameter replacements.
     */
    data class PopToRoute(val staticRoute: String) : NavigationState()
}