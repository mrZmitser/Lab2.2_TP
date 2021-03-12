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
        var sql = "SELECT * FROM buyers GROUP BY id";
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
                SELECT COUNT(height)\s
                FROM buyers\s
                WHERE height=""" + height;
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] sumFemaleWeights() throws SQLException {
        var sql = """
                SELECT SUM(weight)\s
                FROM buyers\s
                WHERE gender='FEMALE'
                """;
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] addShopIdColumn() throws SQLException {
        var sql = "ALTER TABLE buyers ADD shop INT DEFAULT(1) NOT NULL";
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] customQuery(String sql) throws SQLException {
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] connectBuyersShops() throws SQLException {
        var sql = "ALTER TABLE buyers ADD CONSTRAINT shop FOREIGN KEY (shop) REFERENCES shop(id)";
        return formatter.resultToStringArray(getResult(sql));
    }


    public String[] getMinMaxWeights() throws SQLException {
        var sql = "(SELECT MAX(weight) FROM buyers) UNION\n" +
                "(SELECT MIN(weight) FROM buyers)";
        return formatter.resultToStringArray(getResult(sql));
    }

    public String[] getBuyersForShops(int[] shopIds) throws SQLException {
        var sql = """
                SELECT buyers.id, surname, name, phone_number, credit_card, shop, shop.shop_name
                FROM buyers
                INNER JOIN shop ON buyers.shop=shop.id AND (shop.id in """ +
                formatter.intArrayToString(shopIds) + ")";
        return formatter.resultToStringArray(getResult(sql));
    }

    private ResultSet getResult(String sql) throws SQLException {
        var statement = dbConnection.prepareStatement(sql);
        return statement.executeQuery();
    }


}
