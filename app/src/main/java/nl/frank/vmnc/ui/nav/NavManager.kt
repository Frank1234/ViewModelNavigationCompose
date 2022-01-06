package nl.frank.vmnc.ui.nav

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import nl.frank.vmnc.ui.nav.model.NavigationState
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that can navigate by exposing navigation requests to the [NavigationComponent].
 */
@Singleton
class NavManager @Inject constructor() {

    /**
     * Note that I'm using a single state here, and not a list of states.
     *
     * As a result, if you quickly update the state multiple times,
     * the [NavigationComponent] will only receive and handle the latest state.
     */
    private val _navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle())

    val navigationState: StateFlow<NavigationState> = _navigationState

    fun onNavigated(state: NavigationState) {
        // clear navigation state, if state is the current state:
        _navigationState.compareAndSet(state, NavigationState.Idle())
    }

    fun navigate(state: NavigationState) {
        _navigationState.value = state
    }
}