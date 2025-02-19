package overview

import kotlin.test.Test
import kotlin.test.assertEquals

fun resultIsList(items: List<Item>, result: Result) {
    assertEquals(Continue(ListItems(items.toMutableList())), result)
}

fun resultIsError(err: String, result: Result) {
    assertEquals(Continue(Error(err)), result)
}

fun resultIsNoop(result: Result) {
    assertEquals(Continue(Noop), result)
}

class TodoTest {
    @Test fun `help command displays the help text`() {
        val result = Todo().dispatch("help")
        assertEquals(Continue(Help), result)
    }

    class `add command` {
        @Test fun `adds an item to the list`() {
            val todo = Todo()
            resultIsList(listOf<Item>(), todo.dispatch("list"))
            todo.dispatch("add wash car")
            resultIsList(listOf(Item("wash car", State.TODO)), todo.dispatch("list"))
            todo.dispatch("add eat lunch")
            val expected = listOf(
                Item("wash car", State.TODO),
                Item("eat lunch", State.TODO)
            )
            resultIsList(expected, todo.dispatch("list"))
        }

        @Test fun `returns a Continue(Noop)`() {
            val todo = Todo()
            val result = todo.dispatch("add wash car")
            resultIsNoop(result)
        }

        @Test fun `trims any extra whitespace around the description`() {
            val todo = Todo()
            val result = todo.dispatch("add   \twash car  ")
            resultIsNoop(result)
            resultIsList(listOf(Item("wash car", State.TODO)), todo.dispatch("list"))
        }

        private val errorMsg =
            "Add command must have space after add with " +
                "a description that follows.\nExample: add buy hot dogs."

        @Test fun `when there is no description, returns an Error`() {
            val todo = Todo()
            val result = todo.dispatch("add")
            resultIsError(errorMsg, result)
            resultIsList(listOf<Item>(), todo.dispatch("list"))
        }

        @Test fun `when the description is only whitespace, returns an Error`() {
            val todo = Todo()
            val result = todo.dispatch("add  ")
            resultIsError(errorMsg, result)
            resultIsList(listOf<Item>(), todo.dispatch("list"))
        }
    }

    class `done command` {
        @Test fun `it marks an item as done and returns Result(noop, continue)`() {
            val todo = Todo()
            todo.dispatch("add wash car")
            val result = todo.dispatch("done 1")
            resultIsNoop(result)
            resultIsList(listOf(Item("wash car", State.DONE)), todo.dispatch("list"))
        }

        val errorMsg =
            "Done command must have space after done with " +
                "a valid index that follows.\nExample: done 3"

        @Test fun `trims whitespace around index`() {
            val todo = Todo()
            todo.dispatch("add wash car")
            val result = todo.dispatch("done   \t1\t  ")
            resultIsNoop(result)
            resultIsList(listOf(Item("wash car", State.DONE)), todo.dispatch("list"))
        }

        @Test fun `returns an Error if index is out of bounds`() {
            val todo = Todo()
            val result = todo.dispatch("done 1")
            resultIsError(errorMsg, result)
        }

        @Test fun `returns an Error if the index cannot be parsed`() {
            val todo = Todo()
            val result = todo.dispatch("done XIV")
            resultIsError(errorMsg, result)
        }

        @Test fun `returns an Error if the index is missing`() {
            val todo = Todo()
            val result = todo.dispatch("done")
            resultIsError(errorMsg, result)
        }

        @Test fun `returns an Error if there is only whitespace`() {
            val todo = Todo()
            val result = todo.dispatch("done  \t ")
            resultIsError(errorMsg, result)
        }
    }

    @Test fun `list command displays the list of items`() {
        val todo = Todo()
        todo.dispatch("add wash car")
        todo.dispatch("add eat lunch")
        todo.dispatch("done 2")
        val result = todo.dispatch("list")
        val listItems = listOf(
            Item("wash car", State.TODO),
            Item("eat lunch", State.DONE)
        )
        resultIsList(listItems, result)
    }

    @Test fun `quit command returns the Exit Result`() {
        val result = Todo().dispatch("quit")
        assertEquals(Exit, result)
    }

    @Test fun `unknown command returns Continue(Error)`() {
        val result = Todo().dispatch("cure cancer")
        val error =
            "I do not understand your command.  Enter help to display available commands."
        resultIsError(error, result)
    }
}
