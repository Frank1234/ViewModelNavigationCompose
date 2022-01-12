package nl.frank.vmnc.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.navArgument
import nl.frank.vmnc.R
import nl.frank.vmnc.ui.nav.NavRoute
import nl.frank.vmnc.ui.nav.getOrThrow

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object MainPageRoute : NavRoute<MainPageViewModel> {

    /**
     * Returns the NamedNavArguments for this screen. Only needed on the app's start destination.
     */
    fun getNamedNavArgument() = listOf(navArgument(KEY_MAIN_PAGE_INDEX) {
        defaultValue = 0
    })

    data class Arguments(val index: Int)

    override val route = "mainPage/{$KEY_MAIN_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(arguments: Arguments): String =
        route.replace("{$KEY_MAIN_PAGE_INDEX}", "${arguments.index}")

    /**
     * Returns the Arguments from savedStateHandle for this page. Done here to centralize the arguments logic for this page.
     */
    fun getArguments(savedStateHandle: SavedStateHandle) = Arguments(
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

    val title = viewModel.titleViewState
    val count = viewModel.counterViewState

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = viewModel::onNextWithDelayClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.button_next_with_delay),
                style = MaterialTheme.typography.button,
            )
        }
        Button(
            onClick = viewModel::onNextClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.button_next),
                style = MaterialTheme.typography.button,
            )
        }
        Button(
            onClick = viewModel::onPopClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.button_pop),
                style = MaterialTheme.typography.button,
            )
        }
        Text(
            text = "Count $count",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = viewModel::onIncreaseCounterClicked,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.button_increase_counter),
                style = MaterialTheme.typography.button,
            )
        }
    }
}