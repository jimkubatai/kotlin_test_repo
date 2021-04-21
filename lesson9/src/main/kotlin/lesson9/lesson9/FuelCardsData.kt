package lesson9.lesson9

class FuelCardsData {

    private val cards: MutableList<FuelCard> = mutableListOf(FuelCard(101, "Лукойл", "Х000001"),
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

    fun getCardById(cardId: Int): FuelCard? {
        val filterdCars : List<FuelCard> = cards.filter { it.id == cardId }

        if(filterdCars.isEmpty()) {
            return null
        }

        return filterdCars.first()
    }

    fun addCard(card: FuelCard) : Int {
        val newId = cards.maxOf { it.id } + 1
        val newCard = FuelCard(newId, card.brand, card.number)

        cards.add(newCard)

        return newId
    }

    fun removeCard(cardId: Int) : Int {
        val filteredCars : List<FuelCard> = cards.filter { it.id == cardId }

        if(filteredCars.isNotEmpty()) {
            cards.remove(filteredCars.first())
        }

        return cardId
    }

    fun updateCard(card: FuelCard) : Int {
        val filteredCars : List<FuelCard> = cards.filter { it.id == card.id }

        if(filteredCars.isNotEmpty()) {
            cards.remove(filteredCars.first())
            cards.add(card)
        }

        return card.id
    }
}