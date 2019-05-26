package overview

import kotlin.test.Test
import kotlin.test.assertEquals

class TodoTest {
    @Test fun `help command displays the help text`() {
        val result = Todo().dispatch("help")
        assertEquals(Continue(Help), result)
    }

    class `add command` {
        @Test fun `adds an item to the list`() {
            val todo = Todo()
            assertEquals(
                Continue(ListItems(mutableListOf<Item>())),
                todo.dispatch("list")
            )
            todo.dispatch("add wash car")
            assertEquals(
                Continue(ListItems(mutableListOf(Item("wash car", State.TODO)))),
                todo.dispatch("list")
            )
            todo.dispatch("add eat lunch")
            assertEquals(
                Continue(ListItems(mutableListOf(
                        Item("wash car", State.TODO),
                        Item("eat lunch", State.TODO)
                ))),
                todo.dispatch("list")
            )
        }

        @Test fun `returns a Continue(Noop)`() {
            val todo = Todo()
            val result = todo.dispatch("add wash car")
            assertEquals(Continue(Noop), result)
        }

        @Test fun `trims any extra whitespace around the description`() {
            val todo = Todo()
            val result = todo.dispatch("add   \twash car  ")
            assertEquals(Continue(Noop), result)
            assertEquals(
                    Continue(ListItems(mutableListOf(Item("wash car", State.TODO)))),
                    todo.dispatch("list")
            )
        }

        private val errorMsg =
            "Add command must have space after add with " +
                "a description that follows.\nExample: add buy hot dogs."

        @Test fun `when there is no description, returns an Error`() {
            val todo = Todo()
            val result = todo.dispatch("add")
            assertEquals(Continue(Error(errorMsg)), result)
            assertEquals(
                Continue(ListItems(mutableListOf<Item>())),
                todo.dispatch("list")
            )
        }

        @Test fun `when the description is only whitespace, returns an Error`() {
            val todo = Todo()
            val result = todo.dispatch("add  ")
            assertEquals(Continue(Error(errorMsg)), result)
            assertEquals(
                Continue(ListItems(mutableListOf<Item>())),
                todo.dispatch("list")
            )
        }
    }

    class `done command`

    @Test fun `list command displays the list of items`() {
        val todo = Todo()
        todo.dispatch("add wash car")
        todo.dispatch("add eat lunch")
        todo.dispatch("done 2")
        val result = todo.dispatch("list")
        val listItems = ListItems(mutableListOf(
            Item("wash car", State.TODO),
            Item("eat lunch", State.DONE)
        ))
        assertEquals(Continue(listItems), result)
    }

    @Test fun `quit command returns the Exit Result`() {
        val result = Todo().dispatch("quit")
        assertEquals(Exit, result)
    }

    @Test fun `unknown command returns Continue(Error)`() {
        val result = Todo().dispatch("cure cancer")
        val error = Error(
            "I do not understand your command.  Enter help to display available commands."
    )
        assertEquals(Continue(error), result)
    }
}
