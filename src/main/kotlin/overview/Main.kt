package overview

fun main() {
    val todo = Todo()
    loop(todo)
}

fun loop(todo: Todo) {
  println("Enter a command. Enter help to list available commands: ")
  val input = readLine()!!
  val result = todo.dispatch(input)
  when (result) {
      is Exit ->
          return Unit
      is Continue -> {
          result.output.display()
          loop(todo)
      }
  }
}
