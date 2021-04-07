data class Car (var id: Int, val licensePlate: String, val fuelCardNumbers: List<String>) {
    fun car (block: Car.() -> Car) : Car  = this.block()
}