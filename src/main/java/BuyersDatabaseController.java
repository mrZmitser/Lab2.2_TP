import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuyersDatabaseController {
    private Connection dbConnection;
    private final QueriesResultFormatter formatter =
            new QueriesResultFormatter(16, "|", '-');

    // connection
    public void connect(String url, String login, String password) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        dbConnection = DriverManager.getConnection(url, props);
    }

    //queries
    public String[] getFullTable() throws SQLException {
        var sql = "SELECT * FROM buyers";
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] getBuyersByCity(String city) throws SQLException {
        var sql = "SELECT * FROM buyers WHERE city='" + city + "'";
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] getBuyersWithShops() throws SQLException {
        var sql = """
                SELECT buyers.id, surname, name, phone_number, credit_card, shop.shop_name\s
                FROM buyers\s
                INNER JOIN shop ON buyers.shop=shop.id""";
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] countBuyersWithHeight(int height) throws SQLException {
        var sql = """
                SELECT COUNT(""" + height + """
                )\s
                FROM buyers\s
                WHERE height=165""";
        return formatter.resultToStringArray(getResult(sql));
    }


    private ResultSet getResult(String sql) throws SQLException {
        var statement = dbConnection.prepareStatement(sql);
        return statement.executeQuery();
    }


}
