import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

suspend fun main() {

    var totalCars = listOf<Car>()
    var totalCards = listOf<FuelCard>()
    val job1 = GlobalScope.launch {
        delay(1000L)
        totalCars = CarsData().getAll()
    }

    val job2 = GlobalScope.launch {
        delay(1500L)
        totalCards = FuelCardsData().getAll()
    }

    job1.join()
    job2.join()

    println(totalCars)
    println(totalCards)

    val channel = Channel<FuelCard>()

    val job3 = GlobalScope.launch {
        while (this.isActive) {
            for(card in totalCards) {
                delay(5000L)
                channel.send(card)
                println("Sent card $card")
            }
        }
    }

    val job4 = GlobalScope.launch {
        while (this.isActive) {
            delay(1500L)
            val card = channel.receive()
            println("Received card $card")
        }
    }

    delay(15000L)
    println("Time off")
    job3.cancel()
    job4.cancel()
    println("Exit")
}