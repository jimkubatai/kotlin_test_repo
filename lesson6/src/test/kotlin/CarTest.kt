import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CarTest {

    @Test
    fun car() {
          repeat(10) { id ->

              val car = Car(id, "", listOf())

              mockkObject(car)

              every {  car.car {
                  this.id = this.id + 1
                  this
              } } returns Car(id + 1, "", listOf())


              assertEquals(id + 1, car.car {
                  this.id = this.id + 1
                  this
              } .id )
          }
    }
}