import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
        val totalCars = CarsData()
        val totalCards = FuelCardsData()
        val service = Service(totalCards, totalCars)

        repeat(14) {
            if(totalCars.getCarsById(it).any())
            {
                assert(service.getCarLicensePlate(it) == CarsData().getAll()[it].licensePlate)
            }
            else
            {
                assertThrows<NotFoundException> { service.getCarLicensePlate(it) }
            }
        }
    }
}