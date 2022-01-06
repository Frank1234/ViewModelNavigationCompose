package nl.frank.vmnc.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.frank.vmnc.ui.nav.NavigationState
import nl.frank.vmnc.ui.nav.RouteNavigator
import javax.inject.Inject

const val KEY_MAIN_PAGE_INDEX = "MAIN_PAGE_INDEX"

@HiltViewModel
class MainPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // I strongly prefer delegation over inheritance

    private val index = savedStateHandle.get<Int>(KEY_MAIN_PAGE_INDEX)
        ?: throw IllegalArgumentException("Mandatory argument $KEY_MAIN_PAGE_INDEX is missing on opening MainPage.")

    var titleViewState by mutableStateOf("Page $index")
        private set
    var counterViewState by mutableStateOf(0)
        private set

    fun onNextClicked() {
        viewModelScope.launch {
            navigate(NavigationState.NavigateToRoute(MainPageRoute.get(index + 1)))
        }
    }

    fun onNextWithDelayClicked() {
        viewModelScope.launch {
            delay(4000)
            navigate(NavigationState.NavigateToRoute(MainPageRoute.get(index + 1)))
        }
    }

    fun onIncreaseCounterClicked() {
        counterViewState++
    }
}