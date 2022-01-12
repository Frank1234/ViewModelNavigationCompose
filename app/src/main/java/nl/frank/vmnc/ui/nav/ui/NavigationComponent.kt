package nl.frank.vmnc.ui.nav.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import nl.frank.vmnc.ui.main.MainPageRoute

@Composable
fun NavigationComponent(navHostController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navHostController,
        startDestination = MainPageRoute.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        MainPageRoute.composable(
            this,
            navHostController,
            MainPageRoute.getNamedNavArgument() // arguments are only needed on the startDestination of the app
        )
    }
}