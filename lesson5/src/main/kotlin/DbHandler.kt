import Model.Car
import Model.FuelCard
import java.sql.ResultSet
import kotlin.Exception

class DbHandler(private val client: Client) {


    fun getCarById(carId: Int): Car {

        var result: Car? = null
        client.OpenConnection()

        val stm = client.currentConnection.prepareStatement("SELECT * FROM Car WHERE Id = ?")
        stm.setInt(1, carId)

        val reader: ResultSet

        try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }


        try {
            while (reader.next()) {
                val id = reader.getInt("Id")
                val licensePlate = reader.getString("LicensePlate")
                result = Car(id, licensePlate, listOf())
            }
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.CloseConnection()
        }

        if (result == null) throw SqlNoDataException()

        return result
    }

    fun getFuelCardById(cardId: Int): FuelCard {

        var result: FuelCard? = null
        client.OpenConnection()

        val stm = client.currentConnection.prepareStatement("SELECT * FROM FuelCard WHERE Id = ?")
        stm.setInt(1, cardId)

        val reader: ResultSet

        try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }

        try {
            while (reader.next()) {
                val id = reader.getInt("Id")
                val brand = reader.getString("Brand")
                val number = reader.getString("Number")
                result = FuelCard(id, brand, number)
            }
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.CloseConnection()
        }

        if (result == null) throw SqlNoDataException()

        return result
    }

    fun getFuelCardNumberByCarId(carId: Int): Car {

        val result: Car?
        client.OpenConnection()

        val stm = client.currentConnection.prepareStatement(
            "SELECT * FROM Car c " +
                    "LEFT JOIN CarFuelCard cfc ON cfc.Car = c.Id " +
                    "WHERE c.Id = ?"
        )
        stm.setInt(1, carId)

        val reader: ResultSet

        try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }

        val fuelCards: MutableList<String> = mutableListOf()
        var id = 0
        var licensePlate = ""
        try {
            while (reader.next()) {
                id = reader.getInt("Id")
                licensePlate = reader.getString("LicensePlate")
                fuelCards.add(reader.getString("FuelCardNumber"))
            }

            result = Car(id, licensePlate, fuelCards)
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.CloseConnection()
        }

        if (result == null) throw SqlNoDataException()

        return result
    }

    fun getFuelCardsByBrand(brandName: String): Collection<FuelCard> {

        client.OpenConnection()

        val stm = client.currentConnection.prepareStatement("SELECT * FROM FuelCard WHERE Brand = ? ORDER BY Id DESC")

        stm.setString(1, brandName)

        val fuelCards: MutableList<FuelCard> = mutableListOf()

        val reader: ResultSet
        try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }

        try {
            while (reader.next()) {
                val id = reader.getInt("Id")
                val brand = reader.getString("Brand")
                val number = reader.getString("Number")
                fuelCards.add(FuelCard(id, brand, number))
            }
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.CloseConnection()
        }

        return fuelCards
    }

    fun getCarByFuelCardIdOrNull(fuelCardId: Int): Car? {

        val result: Car?

        client.OpenConnection()
        val stm = client.currentConnection.prepareStatement(
            "SELECT c.* FROM FuelCard fc " +
                    "LEFT JOIN CarFuelCard cfc ON fc.Number = cfc.FuelCardNumber " +
                    "LEFT JOIN Car c ON c.Id = cfc.Car " +
                    "WHERE fc.Id = ? " +
                    "GROUP BY c.Id"
        )
        stm.setInt(1, fuelCardId)

        val reader: ResultSet
        try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }

        val fuelCards: MutableList<String> = mutableListOf()
        var id = 0
        var licensePlate = ""

        try {
            while (reader.next()) {
                id = reader.getInt("Id")
                licensePlate = reader.getString("LicensePlate")
            }

            result = Car(id, licensePlate, fuelCards)
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.CloseConnection()
        }

        return result
    }
}