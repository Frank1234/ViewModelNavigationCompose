package nl.frank.vmnc.ui.nav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Navigator to use when initiating navigation from a ViewModel.
 */
interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun popToRoute(route: String)
    fun navigateToRoute(route: String)

    val navigationState: StateFlow<NavigationState>
}

class MyRouteNavigator : RouteNavigator {

    /**
     * Note that I'm using a single state here, not a list of states. As a result, if you quickly
     * update the state multiple times, the view will only receive and handle the latest state,
     * which is fine for my use case.
     */
    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override fun onNavigated(state: NavigationState) {
        // clear navigation state, if state is the current state:
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))

    private fun navigate(state: NavigationState) {
        navigationState.value = state
    }
}