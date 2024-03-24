import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Screen A ${screenModel.value}")
            Button(
                onClick = {
                    screenModel.doSomethingOnScreenA() {
                        navigator.push(ScreenB("some uuid"))
                    }
                }
            ) {
                Text("Navigate to screen B")
            }
        }
    }
}
