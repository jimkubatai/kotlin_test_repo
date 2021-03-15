import Model.CarsData
import Model.FuelCardsData
import java.sql.Connection

fun main() {

    val dbClient = Client("lesson5.s3db");

    dbClient.OpenConnection();

    val dbFiller = DbFiller(dbClient.currentConnection)
    dbFiller.deleteModelTables()
    dbFiller.createModelTables()
    dbFiller.deleteModelTables()

    val totalCars = CarsData()
    val totalCards = FuelCardsData()
    val service = Service(totalCards, totalCars)
}