import model.CarsData
import model.FuelCardsData
import java.lang.Exception

class DbFiller(private val client: Client) {

    fun createModelTables() {
        try {
            client.openConnection()
            val stm = client.currentConnection.createStatement()
            stm.execute("CREATE TABLE \"car\" (\"id\" BIGINT NOT NULL DEFAULT 0, \"licensePlate\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"id\"));")
            stm.execute("CREATE TABLE \"fuelCard\" (\"id\" BIGINT NOT NULL DEFAULT 0, \"brand\" VARCHAR(50) NOT NULL, \"number\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"id\"));")
            stm.execute("CREATE TABLE \"carFuelCard\" (\"id\" BIGINT NOT NULL DEFAULT 0, \"car\" BIGINT NOT NULL, \"fuelCardNumber\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"id\"));")
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        } finally {
            client.closeConnection()
        }
    }

    fun deleteModelTables() {


        try {
            client.openConnection()
            val stm = client.currentConnection.createStatement()
            stm.execute("DROP TABLE \"car\"")
            stm.execute("DROP TABLE \"fuelCard\"")
            stm.execute("DROP TABLE \"carFuelCard\"")
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        } finally {
            client.closeConnection()
        }
    }

    fun fillTables(carsData: CarsData, fuelCardsData: FuelCardsData) {

        val baseQueryCar = "INSERT INTO car (id, licensePlate) VALUES (?, ?)"
        val baseQueryCarFuelCard = "INSERT INTO carFuelCard (id, car, fuelCardNumber) VALUES (?, ?, ?)"
        val baseQueryCarFuel = "INSERT INTO fuelCard (id, brand, number) VALUES (?, ?, ?)"
        var count = 1
        try {
            client.openConnection()

            carsData.getAll().forEach {
                var stm = client.currentConnection.prepareStatement(baseQueryCar)
                stm.setInt(1, it.id)
                stm.setString(2, it.licensePlate)
                stm.execute()
                stm.close()

                it.fuelCardNumbers.forEach { number ->
                    stm = client.currentConnection.prepareStatement(baseQueryCarFuelCard)
                    stm.setInt(1, count)
                    stm.setInt(2, it.id)
                    stm.setString(3, number)
                    stm.execute()
                    stm.close()

                    count = count.inc()
                }
            }

            fuelCardsData.getAll().forEach {
                val stm = client.currentConnection.prepareStatement(baseQueryCarFuel)
                stm.setInt(1, it.id)
                stm.setString(2, it.brand)
                stm.setString(3, it.number)
                stm.execute()
                stm.close()
            }

        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        } finally {
            client.closeConnection()
        }
    }
}