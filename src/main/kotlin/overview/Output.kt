package overview

sealed class Output {
    abstract fun display()
}

object Help : Output() {
    override fun display() {
        println("""
        Available commands:
        help                              Displays this help
        list                              Display the todo list
        add <todo item description>       Adds the time to the todo list
        done <todo item number>           Marks the item as done
        quit                              Exit the program"""
)
    }
}

object Noop : Output() {
    override fun display() {
        return
    }
}

data class Error(val err: String) : Output() {
    override fun display() {
        println(err)
    }
}

data class ListItems(val list: MutableList<Item>) : Output() {
    override fun display() {
        list.forEachIndexed { index, item ->
            if (item.state == State.TODO) {
                println("${index + 1} ${item.description}")
            } else {
                println("${index + 1} ${item.description} (done)")
            }
        }
    }
}
