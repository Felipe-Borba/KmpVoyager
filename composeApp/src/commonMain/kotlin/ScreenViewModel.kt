import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScreenViewModel : ScreenModel {
    private val _state = MutableStateFlow(ScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

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
        screenModelScope.launch {
            _uiEvent.send(UiEvent.GotoNextScreen)
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
    val value: Int = 0,
)

sealed interface ScreenEvent {
    data object Increase : ScreenEvent
    data object Decrease : ScreenEvent
}

sealed class UiEvent {
    data object GotoNextScreen : UiEvent()
}