package nl.frank.vmnc.ui.content

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

data class ContentPageViewState(
    val title: String,
    val counterValue: Int,
)

@HiltViewModel
class ContentPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val routeNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routeNavigator { // prefer delegation over inheritance

    private val index = ContentPageRoute.getIndexFrom(savedStateHandle)

    var viewState by mutableStateOf(
        ContentPageViewState(title = "Page $index", counterValue = 0)
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
        navigateToRoute(ContentPageRoute.get(index + 1))
    }

    fun onIncreaseCounterClicked() {
        viewState = viewState.copy(counterValue = viewState.counterValue + 1)
    }
}