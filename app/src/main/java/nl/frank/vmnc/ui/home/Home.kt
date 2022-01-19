package nl.frank.vmnc.ui.home

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
import nl.frank.vmnc.R
import nl.frank.vmnc.ui.nav.NavRoute
import nl.frank.vmnc.ui.views.PrimaryButton

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */
object HomeRoute : NavRoute<HomeViewModel> {

    override val route = "home/"

    @Composable
    override fun viewModel(): HomeViewModel = hiltViewModel()

    @Composable
    override fun Content(viewModel: HomeViewModel) = Home(viewModel::onStartClicked)
}

/**
 * Just your average Composable, nothing special here.
 */
@Composable
private fun Home(
    onStartClicked: () -> Unit
) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = stringResource(id = R.string.home_title),
            style = MaterialTheme.typography.h6,
        )
        PrimaryButton(
            onClick = onStartClicked,
            title = stringResource(R.string.button_start)
        )
    }
}
