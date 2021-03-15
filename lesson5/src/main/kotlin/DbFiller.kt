import java.sql.Connection

class DbFiller(private val connection: Connection) {

    fun createModelTables() {

        val stm = connection.createStatement()

        stm.execute("CREATE TABLE \"Id\" BIGINT NOT NULL DEFAULT 0, \"LicensePlate\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"Id\"));");
        stm.execute("CREATE TABLE \"FuelCard\" (\"Id\" BIGINT NOT NULL DEFAULT 0, \"Brand\" VARCHAR(50) NOT NULL, \"Number\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"Id\"));");
        stm.execute("CREATE TABLE \"CarFuelCard\" (\"Id\" BIGINT NOT NULL DEFAULT 0, \"Car\" BIGINT NOT NULL, \"FuelCardNumber\" VARCHAR(50) NOT NULL, PRIMARY KEY (\"Id\"));");
    }

    fun deleteModelTables() {

        val stm = connection.createStatement()

        stm.execute("DROP TABLE \"Id\"");
        stm.execute("DROP TABLE \"FuelCard\"");
        stm.execute("DROP TABLE \"CarFuelCard\"");
    }
}