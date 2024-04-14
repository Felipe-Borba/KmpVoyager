import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class ScreenB(
    val textARg: String
) : Screen {
    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
//        val screenModel = rememberScreenModel { ScreenViewModel() }
//        val screenModel = navigator.getNavigatorScreenModel<ScreenViewModel>()

        LifecycleEffect(
            onStarted = {
                println("started")
            },
            onDisposed = {
                println("I'm been disposed :(")
            }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Screen B")
            Text(textARg)

            Button(
                onClick = {
//                    screenModel.onEvent(ScreenEvent.Increase)
                    navigator.pop()
                }
            ) {
                Text("Go back")
            }

            Button(
                onClick = {
                    navigator.replaceAll(TabScreen())
                }
            ) {
                Text("Go to tab screen")
            }
        }
    }
}