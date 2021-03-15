class Service(private val cardsData: FuelCardsData, private val carsData: CarsData) {

    fun getCarToFuelCards(): List<CarToFuelCard> = cardsData.getAll().flatMap { fuelCard ->
        carsData.getCarsByFuelCardNumber(fuelCard).map { car ->
            CarToFuelCard(car.id, car.licensePlate, fuelCard.id, fuelCard.number, fuelCard.brand)
        }
    }

    fun getCarToFuelCardSortedByLicensePlate() : List<CarToFuelCard> = getCarToFuelCards().sortedBy { it.carLicensePlate }

    fun getCarToFuelCardGroupedByVendor() : Map<String, List<CarToFuelCard>> = getCarToFuelCards().groupBy { it.cardVendor.toLowerCase() }

    fun getCarToFuelCardCountByFilter(predicate: (CarToFuelCard) -> Boolean) : Int = getCarToFuelCards().filter(predicate).count()
}