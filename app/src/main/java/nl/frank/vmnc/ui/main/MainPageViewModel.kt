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

data class MainPageViewState(
    val title: String,
    val counterValue: Int,
    val showPopButton: Boolean,
)

@HiltViewModel
class MainPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // I strongly prefer delegation over inheritance

    private val arguments = MainPageRoute.getArgumentsFrom(savedStateHandle)
    private val index = arguments.index

    var viewState by mutableStateOf(
        MainPageViewState(title = "Page $index", counterValue = 0, showPopButton = index != 0)
    )

    fun onNextClicked() {
        navigateToNextPage()
    }

    fun onUpClicked() {
        navigateUp()
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
        viewState = viewState.copy(counterValue = viewState.counterValue + 1)
    }
}