import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class CarsDataTest {

    @Test
    fun getCarsById() {
        val carsData = mockk<CarsData>()

        every { carsData.getCarsById(range(5, Int.MAX_VALUE, fromInclusive = false, toInclusive = true)) } returns emptyList()
        assert(emptyList<Car>() == carsData.getCarsById(10))
        verify { carsData.getCarsById(10) }

        every { carsData.getCarsById(range(1,5, fromInclusive = true, toInclusive = true)) } returns listOf(Car(1, "", listOf()))
        assert(listOf(Car(1, "", listOf())) == carsData.getCarsById(1))
        verify { carsData.getCarsById(1) }

        confirmVerified(carsData)
    }

    @Test
    fun getAll() {
        val carsData = mockk<CarsData>()

        val testResult = listOf(Car(1, "", listOf()))

        every { carsData.getAll() } returns testResult

        assert(testResult == carsData.getAll())

        verify { carsData.getAll() }
        confirmVerified(carsData)
    }
}