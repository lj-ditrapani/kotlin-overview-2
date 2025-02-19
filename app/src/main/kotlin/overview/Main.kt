package overview

fun main() {
    println("\nTodo list\n")
    val todo = Todo()
    readEvalPrintLoop(todo)
}

tailrec fun readEvalPrintLoop(todo: Todo) {
    print("Enter a command. Enter help to list available commands: ")
    val input = readLine()!!
    val result = todo.dispatch(input)
    when (result) {
        is Exit ->
            return Unit
        is Continue -> {
            result.output.display()
            readEvalPrintLoop(todo)
        }
    }
}
