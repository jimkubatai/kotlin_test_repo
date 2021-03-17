import Model.CarsData
import Model.FuelCardsData
import java.lang.Exception

class DbFiller(private val client: Client) {

    fun createModelTables() {
        try {
            client.OpenConnection()
            val stm = client.currentConnection.createStatement()
            stm.execute("CREATE TABLE \"Car\" (\"Id\" BIGINT NOT NULL DEFAULT 0, \"LicensePlate\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"Id\"));")
            stm.execute("CREATE TABLE \"FuelCard\" (\"Id\" BIGINT NOT NULL DEFAULT 0, \"Brand\" VARCHAR(50) NOT NULL, \"Number\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"Id\"));")
            stm.execute("CREATE TABLE \"CarFuelCard\" (\"Id\" BIGINT NOT NULL DEFAULT 0, \"Car\" BIGINT NOT NULL, \"FuelCardNumber\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"Id\"));")
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        } finally {
            client.CloseConnection()
        }
    }

    fun deleteModelTables() {


        try {
            client.OpenConnection()
            val stm = client.currentConnection.createStatement()
            stm.execute("DROP TABLE \"Car\"")
            stm.execute("DROP TABLE \"FuelCard\"")
            stm.execute("DROP TABLE \"CarFuelCard\"")
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        } finally {
            client.CloseConnection()
        }
    }

    fun fillTables(carsData: CarsData, fuelCardsData: FuelCardsData) {

        val baseQueryCar = "INSERT INTO Car (Id, LicensePlate) VALUES (?, ?)"
        val baseQueryCarFuelCard = "INSERT INTO CarFuelCard (Id, Car, FuelCardNumber) VALUES (?, ?, ?)"
        val baseQueryCarFuel = "INSERT INTO FuelCard (Id, Brand, Number) VALUES (?, ?, ?)"
        var count = 1
        try {
            client.OpenConnection()
            for (car in carsData.getAll()) {
                var stm = client.currentConnection.prepareStatement(baseQueryCar)
                stm.setInt(1, car.id)
                stm.setString(2, car.licensePlate)
                stm.execute()
                stm.close()

                for (number in car.fuelCardNumbers) {
                    stm = client.currentConnection.prepareStatement(baseQueryCarFuelCard)
                    stm.setInt(1, count)
                    stm.setInt(2, car.id)
                    stm.setString(3, number)
                    stm.execute()
                    stm.close()

                   count = count.inc()
                }
            }

            for (fuelCard in fuelCardsData.getAll()) {
                val stm = client.currentConnection.prepareStatement(baseQueryCarFuel)
                stm.setInt(1, fuelCard.id)
                stm.setString(2, fuelCard.brand)
                stm.setString(3, fuelCard.number)
                stm.execute()
                stm.close()
            }

        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        } finally {
            client.CloseConnection()
        }
    }
}