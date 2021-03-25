import java.sql.Connection
import java.sql.DriverManager

class Client(private val fileName: String, private val user: String = "", private val password: String = "") {

    lateinit var currentConnection: Connection

    fun openConnection() {
        currentConnection = DriverManager.getConnection("jdbc:sqlite:$fileName", user, password)
    }

    fun closeConnection() {
        currentConnection.close()
    }


}