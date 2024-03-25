import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class ScreenA : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        // To allow multiple instances of screenViewModel
        // val screenModel = rememberScreenModel(tag = "CUSTOM_TAG") { HomeScreenModel() }
        // otherwise just
//        val screenModel = rememberScreenModel { ScreenViewModel() }
//        val screenModel = getScreenModel<ScreenViewModel>()
        val screenModel = navigator.getNavigatorScreenModel<ScreenViewModel>()
        val state by screenModel.state.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Screen A ${state.value}")
            Button(
                onClick = {
                    screenModel.onEvent(ScreenEvent.Increase)
                    navigator.push(ScreenB("some uuid"))
                }
            ) {
                Text("Navigate to screen B")
            }
        }
    }
}
