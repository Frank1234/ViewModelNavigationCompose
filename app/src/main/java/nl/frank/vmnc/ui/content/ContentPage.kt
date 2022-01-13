package nl.frank.vmnc.ui.content

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
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import nl.frank.vmnc.R
import nl.frank.vmnc.ui.nav.NavRoute
import nl.frank.vmnc.ui.nav.getOrThrow
import nl.frank.vmnc.ui.views.PrimaryButton

const val KEY_CONTENT_PAGE_INDEX = "CONTENT_PAGE_INDEX"

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object ContentPageRoute : NavRoute<ContentPageViewModel> {

    override val route = "content/{$KEY_CONTENT_PAGE_INDEX}/"

    /**
     * Returns the route that can be used for navigating to this page.
     */
    fun get(index: Int): String = route.replace("{$KEY_CONTENT_PAGE_INDEX}", "$index")

    fun getIndexFrom(savedStateHandle: SavedStateHandle) =
        savedStateHandle.getOrThrow<Int>(KEY_CONTENT_PAGE_INDEX)

    override fun getArguments(): List<NamedNavArgument> = listOf(
        navArgument(KEY_CONTENT_PAGE_INDEX) { type = NavType.IntType })

    @Composable
    override fun viewModel(): ContentPageViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: ContentPageViewModel) = ContentPage(viewModel)
}

/**
 * Just your average Composable, nothing special here.
 */
@Composable
private fun ContentPage(
    viewModel: ContentPageViewModel
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
