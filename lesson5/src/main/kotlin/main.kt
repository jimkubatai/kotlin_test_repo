import Model.CarsData
import Model.FuelCardsData
import java.lang.Exception

fun main() {

    try {


        val totalCars = CarsData()
        val totalCards = FuelCardsData()
       // val service = Service(totalCards, totalCars)

        val dbClient = Client("lesson5.s3db")

        val dbFiller = DbFiller(dbClient)

        dbFiller.createModelTables()
        dbFiller.fillTables(totalCars, totalCards)

        val dbHandler = DbHandler(dbClient)

        println(dbHandler.getCarByFuelCardIdOrNull(1))
        println(dbHandler.getCarById(1))
        println(dbHandler.getFuelCardById(101))
        println(dbHandler.getFuelCardNumberByCarId(1))
        for(card in dbHandler.getFuelCardsByBrand("Лукойл")) println(card)

        dbFiller.deleteModelTables()
    } catch (ex: Exception)
    {
        println(ex)
        println(ex.cause)
    }
}