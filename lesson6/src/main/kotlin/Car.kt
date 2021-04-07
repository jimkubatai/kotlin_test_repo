data class Car (var id: Int, val licensePlate: String, val fuelCardNumbers: List<String>) {
    fun isContainsFuelCard(carNumber: String) : Boolean {
        return fuelCardNumbers.contains(carNumber)
    }

    fun car (block: Car.() -> Unit) : Unit = block(this)

}