package overview

import kotlin.test.Test
import kotlin.test.assertEquals

class OutputTest {
    class ColorizeTest {
        @Test fun `when color is blue, returns blue message`() {
            val result = colorize(Color.BLUE, "hello")
            assertEquals("\u001B[94mhello\u001B[0m", result)
        }

        @Test fun `when color is green, returns green message`() {
            val result = colorize(Color.GREEN, "hello")
            assertEquals("\u001B[32mhello\u001B[0m", result)
        }

        @Test fun `when color is red, returns red message`() {
            val result = colorize(Color.RED, "hello")
            assertEquals("\u001B[31mhello\u001B[0m", result)
        }

        @Test fun `when color is yellow, returns yellow message`() {
            val result = colorize(Color.YELLOW, "hello")
            assertEquals("\u001B[33mhello\u001B[0m", result)
        }
    }
}
