import java.sql.*;

public class Jdbc {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE book (id BIGINT PRIMARY KEY NOT NULL, name VARCHAR(45), author VARCHAR(45))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertTenBooks(Connection connection) {
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO book VALUES (1, 'Пикник на обочине', 'Стругацкие'), " +
                    "(2, 'Понедельник начинается в субботу', 'Стругацкие'), " +
                    "(3, 'Убик', 'Филип Дик'), " +
                    "(4, 'Исповедь маски', 'Юкио Мисима'), " +
                    "(5, 'Гарри Поттер и Принц Полукровка', 'Джоан Роулинг'), " +
                    "(6, 'Война и мир', 'Толстой'), " +
                    "(7, 'Преступление и наказание', 'Достоевский'), " +
                    "(8, 'Три сестры', 'Чехов'), " +
                    "(9, 'Муму', 'Тургенев'), " +
                    "(10, 'Идиот', 'Достоевский');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showByAuthor(String authorName, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE author LIKE ?")) {
            statement.setString(1, authorName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        createTable(connection);
        insertTenBooks(connection);
        showByAuthor("Стругацкие", connection);
        connection.close();
    }
}
