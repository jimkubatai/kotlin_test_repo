class FuelCardsData {

    private val cards: List<FuelCard> = listOf(FuelCard(101, "Лукойл", "Х000001"),
        FuelCard(102, "Газпром", "W100001"),
        FuelCard(103, "Лукойл", "Х000002"),
        FuelCard(103, "Лукойл", "Х000003"),
        FuelCard(104, "Газпром", "W100002"),
        FuelCard(105, "Газпром", "W100003"),
        FuelCard(106, "Газпром", "W100004"),
        FuelCard(107, "Газпром", "W100005"),
        FuelCard(108, "Лукойл", "Х000004"),
        FuelCard(109, "Shell", "SHELL99001"),
        FuelCard(110, "Татнефть", "40150499201"),
        FuelCard(111, "Shell", "SHELL99002"),
        FuelCard(112, "Татнефть", "40150499202"),
        FuelCard(113, "Shell", "SHELL99003"))


    fun getAll(): List<FuelCard> = this.cards

    fun getCardsByCar(car: Car): List<FuelCard> = cards.filter { car.fuelCardNumbers.contains(it.number) }


    fun getCardByCar(car: Car): FuelCard = cards.first { car.fuelCardNumbers.contains(it.number) }


    fun getCardByCarOrNull(car: Car): FuelCard? = cards.firstOrNull { car.fuelCardNumbers.contains(it.number) }

}