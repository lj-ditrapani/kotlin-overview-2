package overview

sealed class Result
object Exit: Result()
data class Continue(val output: Output): Result()

enum class State {
    TODO, DONE
}

data class Item(val description: String, val state: State)

class Todo {
    private val list = mutableListOf<Item>()

    fun dispatch(command: String): Result {
        val c = command.trim()
        return when (firstWord(c)) {
            "help" -> Continue(Help)
            "list" -> Continue(List(list))
            "add" -> Continue(add(c))
            "done" -> Continue(done(c))
            "quit" -> Exit
            else -> Continue(Error(
                    "I do not understand your command.  Enter help to display available commands."
            ))
        }
    }

    private fun add(line: String): Output = Noop

    private fun done(line: String): Output = Noop

    private fun firstWord(line: String): String = line.split(' ')[0]
}
