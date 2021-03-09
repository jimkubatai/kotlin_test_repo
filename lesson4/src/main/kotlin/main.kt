fun main() {
    val totalCars = CarsData()
    val totalCards = FuelCardsData()
    val service = Service(totalCards, totalCars)

    println(service.getCarToFuelCards())
    println(service.getCarToFuelCardGroupedByVendor())
    println(service.getCarToFuelCardSortedByLicensePlate())
    println(service.getCarToFuelCardByFilter { it.cardVendor.toLowerCase() == "газпром" })
}