package nl.frank.vmnc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import nl.frank.vmnc.ui.nav.ui.NavigationComponent
import nl.frank.vmnc.ui.theme.ViewModelNavigationComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ViewModelNavigationComposeTheme {
                Scaffold {
                    NavigationComponent(navController, it)
                }
            }
        }
    }
}