import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ScreenViewModel : ScreenModel {
    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: ScreenEvent) {
        when (event) {
            ScreenEvent.Decrease -> decreaseEvent()
            ScreenEvent.Increase -> increaseEvent()
        }
    }

    private fun increaseEvent() {
        _state.update {
            it.copy(value = it.value + 1)
        }
    }

    private fun decreaseEvent() {
        _state.update {
            it.copy(value = it.value - 1)
        }
    }

    override fun onDispose() {
        super.onDispose()
        println("screen view model is been disposed")
    }
}

data class ScreenState(
    val value: Int = 0
)

sealed interface ScreenEvent {
    data object Increase : ScreenEvent
    data object Decrease : ScreenEvent
}