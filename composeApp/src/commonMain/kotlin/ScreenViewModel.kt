import cafe.adriel.voyager.core.model.ScreenModel

class ScreenViewModel : ScreenModel {
    var value = 0;

    fun doSomethingOnScreenA(callback: () -> Unit) {
        value++
        println("doSomethingOnScreenA $value")
        return callback.invoke()
    }

    fun doSomethingOnScreenB() {
        value++
        println("doSomethingOnScreenB $value")
    }

    override fun onDispose() {
        super.onDispose()
        println("screen view model is been disposed")
    }
}