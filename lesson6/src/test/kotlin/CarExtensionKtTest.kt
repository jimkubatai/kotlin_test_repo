import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class CarExtensionKtTest {

    @Test
    fun isWrong() {
        repeat(10) {
            val car = Car(it, "", listOf())

            if (it == 2) assertThrows<WrongIdException> { car.isWrong() }
            else assert(!car.isWrong())
        }

    }
}