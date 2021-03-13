import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

class BuyersDatabaseControllerTest {
    final static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    final static String DB_USER_LOGIN = "postgres";
    final static String DB_USER_PASSWORD = "password";

    @BeforeAll
    public static void migrate() {
        Flyway flyway = Flyway.configure().dataSource(DB_URL, DB_USER_LOGIN, DB_USER_PASSWORD)
                .load();
        flyway.clean();
        var result = flyway.migrate();
        System.out.println(result.migrationsExecuted);
    }

    public static BuyersDatabaseController connect() {
        BuyersDatabaseController dbController = new BuyersDatabaseController();
        try {
            dbController.connect(DB_URL, DB_USER_LOGIN, DB_USER_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbController;
    }

    private void printStrings(String[] strings) {
        Arrays.stream(strings).forEach(System.out::println);
    }


    @Test
    void getFullTable() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');
        System.out.println("Full Table");
        try {
            printStrings(dbController.getFullTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));

    }

    @Test
    void getBuyersByCity() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Buyers from Brest");
        try {
            printStrings(dbController.getBuyersByCity("Brest"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));

    }

    @Test
    void getBuyersWithShops() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Buyers with shops in specified format");
        try {
            printStrings(dbController.getBuyersWithShops());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));

    }


    @Test
    void countBuyersWithHeight() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Count height = 175");
        try {
            printStrings(dbController.countBuyersWithHeight(175));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));
    }

    @Test
    void sumFemaleWeights() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Sum female weights");
        try {
            printStrings(dbController.sumFemaleWeights());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));
    }


    void addShopIdColumn() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Add Shop column");
        try {
            dbController.addShopIdColumn();
            printStrings(dbController.getFullTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));
    }

    @Test
    void createAndConnectShop(){
        addShopIdColumn();
        connectBuyersShops();
    }

    void connectBuyersShops() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Connect buyers and shops, update some buyers");
        var updatingSql = """ 
                UPDATE buyers SET shop=2 WHERE id IN (5, 6);\s
                UPDATE buyers SET shop=4 WHERE id IN (3, 4);
                """;
        try {
            dbController.customUpdateQuery(updatingSql);
            dbController.connectBuyersShops();
            printStrings(dbController.getFullTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));
    }

    @Test
    void getMaxMinWeights() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Min and Max weights");
        try {
            printStrings(dbController.getMinMaxWeights());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));
    }

    @Test
    void getBuyersForShops() {
        BuyersDatabaseController dbController = connect();
        var formatter = new QueriesResultFormatter(16, "|", '*');

        System.out.println("Buyers for shops 2, 4");
        try {
            printStrings(dbController.getBuyersForShops(new int[]{2, 4}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(formatter.fillingString(255));
    }
}