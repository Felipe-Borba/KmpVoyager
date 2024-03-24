import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(di)
    }) {
        MaterialTheme {
            Navigator(
                screen = ScreenA(),
                onBackPressed = { currentScreen ->
                    println("Back Pressed on screen ${currentScreen.key}")
                    true // if false won't pop current screen
                }
            ) { navigator ->
                Scaffold(
                    topBar = { TopAppBar { Text("Voyager") } }
                ) { innerPadding ->
                    SlideTransition(
                        navigator = navigator,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
