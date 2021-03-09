class Service(private val cardsData: FuelCardsData, private val carsData: CarsData) {

    fun getCarToFuelCards() : List<CarToFuelCard> {
        val listOfItems = mutableListOf<CarToFuelCard>()

        for(card in cardsData.getAll()) {
            for(car in carsData.getCarsByFuelCardNumber(card)) {
                val item = CarToFuelCard(car.id, car.licensePlate, card.id, card.number, card.brand)
                listOfItems.add(item)
            }
        }

        return listOfItems.toList()
    }

    fun getCarToFuelCardSortedByLicensePlate() : List<CarToFuelCard> {
        return getCarToFuelCards().sortedBy { it.carLicensePlate }
    }
    fun getCarToFuelCardGroupedByVendor() : Map<String, List<CarToFuelCard>>  {
        return getCarToFuelCards().groupBy { it.cardVendor.toLowerCase() }
    }

    fun getCarToFuelCardCountByFilter(predicate: (CarToFuelCard) -> Boolean) : Int {
        return getCarToFuelCards().filter(predicate).count()
    }
}