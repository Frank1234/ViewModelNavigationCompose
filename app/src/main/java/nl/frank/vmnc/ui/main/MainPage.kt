package nl.frank.vmnc.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import nl.frank.vmnc.R
import nl.frank.vmnc.ui.nav.NavRoute
import nl.frank.vmnc.ui.nav.getOrThrow
import nl.frank.vmnc.ui.views.PrimaryButton

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object MainPageRoute : NavRoute<MainPageViewModel> {

    /**
     * Arguments to use when opening this page. This is used to make the arguments more strongly typed.
     */
    data class Arguments(val index: Int)

    override val route = "mainPage/{$KEY_MAIN_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(arguments: Arguments): String =
        route.replace("{$KEY_MAIN_PAGE_INDEX}", "${arguments.index}")

    /**
     * Returns this page's arguments as parsed from [SavedStateHandle]
     */
    fun getArgumentsFrom(savedStateHandle: SavedStateHandle) = Arguments(
        index = savedStateHandle.getOrThrow(KEY_MAIN_PAGE_INDEX)
    )

    @Composable
    override fun viewModel(): MainPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: MainPageViewModel) = MainPage(viewModel)
}

/**
 * Just your average Composable, nothing special here.
 */
@Composable
fun MainPage(
    viewModel: MainPageViewModel
) {
    val viewState = viewModel.viewState

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = viewState.title,
            style = MaterialTheme.typography.h6,
        )
        PrimaryButton(
            onClick = viewModel::onNextWithDelayClicked,
            title = stringResource(R.string.button_next_with_delay)
        )
        PrimaryButton(
            onClick = viewModel::onNextClicked,
            title = stringResource(R.string.button_next)
        )
        if (viewState.showPopButton)
            PrimaryButton(
                onClick = viewModel::onUpClicked,
                title = stringResource(R.string.button_up)
            )

        Text(
            text = "Count ${viewState.counterValue}",
            style = MaterialTheme.typography.h6,
        )
        PrimaryButton(
            onClick = viewModel::onIncreaseCounterClicked,
            title = stringResource(R.string.button_increase_counter)
        )
    }
}
