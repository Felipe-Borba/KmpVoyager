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

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(ScreenA()) { navigator ->
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
