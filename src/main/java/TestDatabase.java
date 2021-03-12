import java.sql.*;
import java.util.Arrays;
import java.util.Properties;

public class TestDatabase {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","password");
        Connection conn = DriverManager.getConnection(url, props);
        var statement = conn.prepareStatement("select * from buyers");
        var set = statement.executeQuery();
        System.out.println(set.getArray(0).getArray());
        System.out.println(conn.getClientInfo().elements());
    }
}
