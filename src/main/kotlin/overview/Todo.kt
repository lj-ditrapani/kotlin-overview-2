package overview

sealed class Result
object Exit : Result()
data class Continue(val output: Output) : Result()

enum class State {
    TODO, DONE
}

data class Item(val description: String, val state: State)

sealed class Command {
    abstract val firstWord: String
}
data class NoArgCommand(override val firstWord: String): Command()
data class CommandWithArg(override val firstWord: String, val arg: String): Command()

class Todo {
    private val list = mutableListOf<Item>()

    fun dispatch(line: String): Result {
        val command = parse(line)
        return when (command.firstWord) {
            "help" -> Continue(Help)
            "list" -> Continue(ListItems(list))
            "add" -> Continue(add(command))
            "done" -> Continue(done(command))
            "quit" -> Exit
            else -> Continue(Error(
                    "I do not understand your command.  " +
                    "Enter help to display available commands."
            ))
        }
    }

    private fun add(command: Command): Output = when (command) {
        is NoArgCommand -> Error(
        "Add command must have space after add with " +
          "a description that follows.\nExample: add buy hot dogs."
  )
        is CommandWithArg -> {
            list.add(Item(command.arg, State.TODO))
            Noop
        }
    }

    private fun done(command: Command): Output = Noop

    private fun parse(line: String): Command {
        val parts = line.trim().split(' ', limit = 2)
        return when (parts.size) {
            2 -> CommandWithArg(parts[0], parts[1].trim())
            else -> NoArgCommand(parts[0])
        }
    }
}
