import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class CarsDataTest {

    @Test
    fun getCarsById() {
        val carsData = mockk<CarsData>()
        every { carsData.getCarsById(1) } returns listOf(Car(1, "abc", listOf()))
    }
}