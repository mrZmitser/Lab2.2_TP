import data.Buyer;
import data.Shop;

import java.sql.*;
import java.util.Properties;

public class BuyersDao {
    private Connection dbConnection;
    private final QueriesResultFormatter formatter = new QueriesResultFormatter(this);

    // connection
    public void connect(String url, String login, String password) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);
        dbConnection = DriverManager.getConnection(url, props);
    }

    //queries
    public Buyer[] getFullTable() throws SQLException {
        var sql = "SELECT * FROM buyers GROUP BY id";
        return formatter.resultToBuyerArray(getResult(sql));
    }

    public Buyer[] getBuyersByCity(String city) throws SQLException {
        var sql = "SELECT * FROM buyers WHERE city='" + city + "'";
        return formatter.resultToBuyerArray(getResult(sql));
    }

    public Buyer[] getBuyersWithShops() throws SQLException {
        var sql = """
                SELECT buyers.id, surname, name, phone_number, credit_card, shop.shop_name\s
                FROM buyers\s
                INNER JOIN shop ON buyers.shop=shop.id""";
        return formatter.resultToBuyerArray(getResult(sql));
    }

    public int countBuyersWithHeight(int height) throws SQLException {
        var sql = """
                SELECT COUNT(height)\s
                FROM buyers\s
                WHERE height=""" + height;
        var result = getResult(sql);
        result.next();
        return Integer.parseInt(result.getString(1));
    }

    public int sumFemaleWeights() throws SQLException {
        var sql = """
                SELECT SUM(weight)\s
                FROM buyers\s
                WHERE gender='FEMALE'
                """;
        var result = getResult(sql);
        result.next();
        return Integer.parseInt(result.getString(1));
    }

    public Buyer[] getMinMaxWeights() throws SQLException {
        var sql = "(SELECT MAX(weight) FROM buyers) UNION\n" +
                "(SELECT MIN(weight) FROM buyers)";
        return formatter.resultToBuyerArray(getResult(sql));
    }

    public Buyer[] getBuyersForShops(int[] shopIds) throws SQLException {
        var sql = """
                SELECT buyers.id, surname, name, phone_number, credit_card, shop, shop.shop_name
                FROM buyers
                INNER JOIN shop ON buyers.shop=shop.id AND (shop.id in """ +
                formatter.intArrayToString(shopIds) + ")";
        return formatter.resultToBuyerArray(getResult(sql));
    }

    private ResultSet getResult(String sql) throws SQLException {
        var statement = dbConnection.prepareStatement(sql);
        return statement.executeQuery();
    }

    public Shop getShop(int id) throws SQLException {
        var sql = "SELECT * FROM shop WHERE id=" + id;
        var result = getResult(sql);
        result.next();
        return formatter.resultToShop(result);
    }
}
