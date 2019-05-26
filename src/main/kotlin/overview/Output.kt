package overview

enum class Color {
    BLUE, GREEN, RED, YELLOW
}

fun colorize(color: Color, message: String): String {
    val code = when (color) {
        Color.BLUE -> "94"
        Color.GREEN -> "32"
        Color.RED -> "31"
        Color.YELLOW -> "33"
    }
    return "\u001B[${code}m${message}\u001B[0m"
}

sealed class Output {
    abstract fun display()
}

object Help : Output() {
    override fun display() {
        println(colorize(Color.YELLOW, """
    Available commands:
        help                              Displays this help
        list                              Display the todo list
        add <todo item description>       Adds the time to the todo list
        done <todo item number>           Marks the item as done
        quit                              Exit the program"""
))
    }
}

object Noop : Output() {
    override fun display() {
        return
    }
}

data class Error(val err: String) : Output() {
    override fun display() {
        println(colorize(Color.RED, err))
    }
}

data class ListItems(val list: MutableList<Item>) : Output() {
    override fun display() {
        list.forEachIndexed { index, item ->
            if (item.state == State.TODO) {
                val message = colorize(Color.GREEN, item.description)
                println("${index + 1} $message")
            } else {
                val message = colorize(Color.BLUE, item.description)
                println("${index + 1} $message (done)")
            }
        }
    }
}
