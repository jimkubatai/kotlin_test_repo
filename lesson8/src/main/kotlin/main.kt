fun main() {
    val totalCars = CarsData()
    val totalCards = FuelCardsData()
    val service = Service(totalCards, totalCars)

    println(service.getCarLicensePlate(1))
    println(service.isCarExist(1))
}