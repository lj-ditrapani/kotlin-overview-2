package overview

enum class Color {
    RED,
    GREEN,
    YELLOW,
    BLUE,
}

fun colorize(
    color: Color,
    message: String,
): String {
    val code =
        when (color) {
            Color.RED -> "31"
            Color.GREEN -> "32"
            Color.YELLOW -> "33"
            Color.BLUE -> "34"
        }
    return "\u001B[${code}m${message}\u001B[0m"
}

sealed class Output {
    abstract fun display()
}

object Help : Output() {
    override fun display() {
        println(
            colorize(
                Color.YELLOW,
                """
        Available commands:
        help                              Displays this help
        list                              Display the todo list
        add <todo item description>       Adds the item to the todo list
        done <todo item number>           Marks the item as done
        quit                              Exit the program""",
            ),
        )
    }
}

object Noop : Output() {
    override fun display() = Unit
}

data class Error(
    val err: String,
) : Output() {
    override fun display() {
        println(colorize(Color.RED, err))
    }
}

data class ListItems(
    val list: MutableList<Item>,
) : Output() {
    override fun display() {
        list.forEachIndexed { index, item ->
            when (item.state) {
                State.TODO -> {
                    val message = colorize(Color.GREEN, item.description)
                    println("${index + 1} $message")
                }
                State.DONE -> {
                    val message = colorize(Color.BLUE, item.description)
                    println("${index + 1} $message (done)")
                }
            }
        }
    }
}
