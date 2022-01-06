package nl.frank.vmnc.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

interface NavRoute<T : RouteNavigator> {

    val route: String

    @Composable
    fun Content(viewModel: T)

    @Composable
    fun viewModel(): T

    /**
     * Generates the composable for this route.
     */
    fun composable(
        builder: NavGraphBuilder,
        navHostController: NavHostController,
        arguments: List<NamedNavArgument> = listOf()
    ) {
        builder.composable(route, arguments) {
            val viewModel = viewModel()
            val viewStateAsState by viewModel.navigationState.collectAsState()

            LaunchedEffect(viewStateAsState) {
                Log.d("Nav", "${this@NavRoute} updateNavigationState to $viewStateAsState")
                updateNavigationState(navHostController, viewStateAsState, viewModel::onNavigated)
            }

            Content(viewModel)
        }
    }

    /**
     * Navigates to viewState.
     */
    private fun updateNavigationState(
        navHostController: NavHostController?,
        navigationState: NavigationState,
        onNavigated: (navState: NavigationState) -> Unit,
    ) {
        when (navigationState) {
            is NavigationState.NavigateToRoute -> {
                navHostController?.navigate(navigationState.route)
                onNavigated(navigationState)
            }
            is NavigationState.PopToRoute -> {
                navHostController?.popBackStack(navigationState.staticRoute, false)
                onNavigated(navigationState)
            }
            is NavigationState.Idle -> {
            }
        }
    }
}