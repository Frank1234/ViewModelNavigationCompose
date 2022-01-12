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
import nl.frank.vmnc.ui.nav.RouteNavigator
import javax.inject.Inject

const val KEY_MAIN_PAGE_INDEX = "MAIN_PAGE_INDEX"

@HiltViewModel
class MainPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // I strongly prefer delegation over inheritance

    private val index = MainPageRoute.getArguments(savedStateHandle).index

    var titleViewState by mutableStateOf("Page $index")
        private set
    var counterViewState by mutableStateOf(0)
        private set

    fun onNextClicked() {
        navigateToNextPage()
    }

    fun onNextWithDelayClicked() {
        viewModelScope.launch {
            delay(4000)
            navigateToNextPage()
        }
    }

    private fun navigateToNextPage() {
        navigateToRoute(MainPageRoute.get(MainPageRoute.Arguments(index + 1)))
    }

    fun onIncreaseCounterClicked() {
        counterViewState++
    }
}