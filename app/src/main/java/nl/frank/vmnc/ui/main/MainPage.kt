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
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nl.frank.vmnc.R

object MainPageRoute {

    val route = "mainPage/{$KEY_MAIN_PAGE_INDEX}/"

    fun composable(
        builder: NavGraphBuilder,
        arguments: List<NamedNavArgument> = listOf()
    ) {
        builder.composable(route, arguments) { // TODO args not needed in route?
            MainPage(hiltViewModel())
        }
    }

    fun get(index: Int) = route.replace("{$KEY_MAIN_PAGE_INDEX}", "$index")
}

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