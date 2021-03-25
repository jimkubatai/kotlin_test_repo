class Service(private val cardsData: FuelCardsData, private val carsData: CarsData) {

    fun isCarExist(carId : Int) : Boolean {

        if (carsData.getAll().filter { car: Car -> car.id == carId }.any()) {
            return  true
        }

        return false
    }

    fun getCarLicensePlate(carId : Int) : String {

        val result = carsData.getAll().filter { car: Car -> car.id == carId }
        if (result.any()) {
            return  result.first().licensePlate
        }

       throw NotFoundException("not found id")
    }


}