package nl.frank.vmnc.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nl.frank.vmnc.ui.content.ContentPageRoute
import nl.frank.vmnc.ui.nav.RouteNavigator
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    fun onStartClicked() {
        // here we initiate navigation:
        navigateToRoute(ContentPageRoute.get(0))
    }
}