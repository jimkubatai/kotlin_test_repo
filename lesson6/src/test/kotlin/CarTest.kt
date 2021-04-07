import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class CarTest {

    @Test
    fun car() {
        repeat(10) { operandId ->
            val car = mockk<Car>()

            assert(operandId + 1 == car.id)
        }

    }

    fun incCarId (it: Car): Car {
        it.id = it.id + 1
        return it
    }

}