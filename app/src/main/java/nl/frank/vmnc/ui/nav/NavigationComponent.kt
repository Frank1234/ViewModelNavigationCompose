package nl.frank.vmnc.ui.nav.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import nl.frank.vmnc.ui.main.KEY_MAIN_PAGE_INDEX
import nl.frank.vmnc.ui.main.MainPageRoute
import nl.frank.vmnc.ui.nav.NavManager
import nl.frank.vmnc.ui.nav.model.NavigationState

@Composable
fun NavigationComponent(
    navHostController: NavHostController,
    navManager: NavManager,
    paddingValues: PaddingValues
) {

    // listen to + handle navigation requests:
    val viewStateAsState by navManager.navigationState.collectAsState()
    LaunchedEffect(viewStateAsState) {
        updateNavigationState(
            navHostController,
            viewStateAsState,
            navManager::onNavigated
        )
    }

    NavHost(
        navController = navHostController,
        startDestination = MainPageRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        MainPageRoute.composable(
            this,
            arguments = listOf(navArgument(KEY_MAIN_PAGE_INDEX) {
                defaultValue = 0
            }) // arguments are only added here to set a default for the app's start destination
        )
        navigation(startDestination = "username", route = "login") {

        }
    }
}

/**
 * Navigates to [navigationState].
 */
private fun updateNavigationState(
    navHostController: NavHostController?,
    navigationState: NavigationState,
    onNavigated: (navState: NavigationState) -> Unit,
) {
    Log.d("Nav", "updateNavigationState to $navigationState")
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