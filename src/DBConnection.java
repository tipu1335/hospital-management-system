import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String USERNAME = "root";

    public static Connection getConnection() throws SQLException {
        String password = getPassword();
        return DriverManager.getConnection(URL, USERNAME, password);
    }

    private static String getPassword() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fis);
            return props.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Could not read db.properties file: " + e.getMessage());
            return "";
        }
    }
}