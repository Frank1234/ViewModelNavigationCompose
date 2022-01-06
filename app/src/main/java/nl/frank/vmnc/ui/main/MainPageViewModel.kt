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
import nl.frank.vmnc.ui.nav.NavManager
import nl.frank.vmnc.ui.nav.model.NavigationState
import javax.inject.Inject

const val KEY_MAIN_PAGE_INDEX = "MAIN_PAGE_INDEX"

@HiltViewModel
class MainPageViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navManager: NavManager,
) : ViewModel() {

    private val index = savedStateHandle.get<Int>(KEY_MAIN_PAGE_INDEX)
        ?: throw IllegalArgumentException("Mandatory argument $KEY_MAIN_PAGE_INDEX is missing on opening MainPage.")

    var titleViewState by mutableStateOf("Page $index")
        private set
    var counterViewState by mutableStateOf(0)
        private set

    fun onNextClicked() {
        viewModelScope.launch {
            navManager.navigate(NavigationState.NavigateToRoute(MainPageRoute.get(index + 1)))
        }
    }

    fun onNextWithDelayClicked() {
        viewModelScope.launch {
            delay(5000)
            navManager.navigate(NavigationState.NavigateToRoute(MainPageRoute.get(index + 1)))
        }
    }

    fun onIncreaseCounterClicked() {
        counterViewState++
    }
}