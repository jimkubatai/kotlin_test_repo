package model

class CarsData {
    private val cars: List<Car> = listOf(
        Car(1, "Х685АЕ790", listOf("Х000001")),
                  Car(2, "Р585УР190", listOf("W100001", "Х000002")),
                  Car(3, "М210РР777", listOf("Х000003", "W100002", "Х000001")),
                  Car(4, "В491ХУ77", listOf("SHELL99003", "Х000003", "W100002", "Х000001", "W100001")),
                  Car(5, "С722ТС62", listOf("Х000003", "W100002", "Х000001")),
                  Car(6, "Т222ТТ190", listOf("Х000004", "40150499201")),
                  Car(7, "Х832ХХ90", listOf("W100005")),
                  Car(8, "М872ММ77", listOf("W100003")),
                  Car(9, "Н241ТВ55", listOf("W100004")),
                  Car(10, "С243СР777", listOf("40150499202", "SHELL99002"))
    )

    fun getAll(): List<Car> = this.cars
}