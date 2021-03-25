import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class ServiceTest {

    @Test
    fun isCarExist() {
        val totalCars = CarsData()
        val totalCards = FuelCardsData()
        val service = Service(totalCards, totalCars)

        repeat(14) {
            if(totalCars.getCarsById(it).any())
            {
                assert(service.isCarExist(it))
            }
            else
            {
                assertThrows<NotFoundException> { service.isCarExist(it) }
            }
        }
    }

    @Test
    fun getCarLicensePlate() {
    }
}