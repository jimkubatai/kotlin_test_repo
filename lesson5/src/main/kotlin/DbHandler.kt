import model.Car
import model.FuelCard
import java.sql.ResultSet
import kotlin.Exception

class DbHandler(private val client: Client) {


    fun getCarById(carId: Int): Car {

        var result: Car? = null
        client.openConnection()

        val stm = client.currentConnection.prepareStatement("SELECT * FROM car WHERE id = ?")
        stm.setInt(1, carId)

        val reader: ResultSet = try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }


        try {
            while (reader.next()) {
                val id = reader.getInt("id")
                val licensePlate = reader.getString("licensePlate")
                result = Car(id, licensePlate, listOf())
            }
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.closeConnection()
        }

        if (result == null) throw SqlNoDataException()

        return result
    }

    fun getFuelCardById(cardId: Int): FuelCard {

        var result: FuelCard? = null
        client.openConnection()

        val stm = client.currentConnection.prepareStatement("SELECT * FROM fuelCard WHERE Id = ?")
        stm.setInt(1, cardId)

        val reader: ResultSet = try {
            reader = stm.executeQuery()
        } catch (ex: Exception) {
            throw SqlExecuteException(ex)
        }

        try {
            while (reader.next()) {
                val id = reader.getInt("id")
                val brand = reader.getString("brand")
                val number = reader.getString("number")
                result = FuelCard(id, brand, number)
            }
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.closeConnection()
        }

        if (result == null) throw SqlNoDataException()

        return result
    }

    fun getFuelCardNumberByCarId(carId: Int): Car {

        val result: Car?
        client.openConnection()

        val stm = client.currentConnection.prepareStatement(
            "SELECT * FROM car c " +
                    "LEFT JOIN carFuelCard cfc ON cfc.car = c.id " +
                    "WHERE c.id = ?"
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
                id = reader.getInt("id")
                licensePlate = reader.getString("licensePlate")
                fuelCards.add(reader.getString("fuelCardNumber"))
            }

            result = Car(id, licensePlate, fuelCards)
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.closeConnection()
        }

        if (result == null) throw SqlNoDataException()

        return result
    }

    fun getFuelCardsByBrand(brandName: String): Collection<FuelCard> {

        client.openConnection()

        val stm = client.currentConnection.prepareStatement("SELECT * FROM fuelCard WHERE brand = ? ORDER BY id DESC")

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
                val id = reader.getInt("id")
                val brand = reader.getString("brand")
                val number = reader.getString("number")
                fuelCards.add(FuelCard(id, brand, number))
            }
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.closeConnection()
        }

        return fuelCards
    }

    fun getCarByFuelCardIdOrNull(fuelCardId: Int): Car? {

        val result: Car?

        client.openConnection()
        val stm = client.currentConnection.prepareStatement(
            "SELECT c.* FROM fuelCard fc " +
                    "LEFT JOIN carFuelCard cfc ON fc.number = cfc.fuelCardNumber " +
                    "LEFT JOIN car c ON c.id = cfc.car " +
                    "WHERE fc.id = ? " +
                    "GROUP BY c.id"
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
                id = reader.getInt("id")
                licensePlate = reader.getString("licensePlate")
            }

            result = Car(id, licensePlate, fuelCards)
        } catch (ex: Exception) {
            throw SqlReadDataException(ex)
        } finally {
            reader.close(); stm.close(); client.closeConnection()
        }

        return result
    }
}