package nl.frank.vmnc.ui.nav.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import nl.frank.vmnc.ui.home.HomeRoute
import nl.frank.vmnc.ui.content.ContentPageRoute

@Composable
fun NavigationComponent(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = HomeRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        HomeRoute.composable(this, navHostController)
        ContentPageRoute.composable(
            this, navHostController
        )
    }
}