import data.Buyer;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

class BuyersDaoTest {
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

    public static BuyersDao connect() {
        BuyersDao dbController = new BuyersDao();
        try {
            dbController.connect(DB_URL, DB_USER_LOGIN, DB_USER_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbController;
    }

    private void printBuyers(Buyer[] buyers) {
        Arrays.stream(buyers).forEach(System.out::println);
    }


    @Test
    void getFullTable() {
        BuyersDao dbController = connect();
        System.out.println("Full Table");
        try {
            printBuyers(dbController.getFullTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getBuyersByCity() {
        BuyersDao dbController = connect();

        System.out.println("Buyers from Brest");
        try {
            printBuyers(dbController.getBuyersByCity("Brest"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getBuyersWithShops() {
        BuyersDao dbController = connect();

        System.out.println("Buyers with shops in specified format");
        try {
            printBuyers(dbController.getBuyersWithShops());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }


    @Test
    void countBuyersWithHeight() {
        BuyersDao dbController = connect();

        System.out.println("Count height = 175");
        try {
            System.out.println(dbController.countBuyersWithHeight(175));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void sumFemaleWeights() {
        BuyersDao dbController = connect();
        System.out.println("Sum female weights");
        try {
            System.out.println(dbController.sumFemaleWeights());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getMaxMinWeights() {
        BuyersDao dbController = connect();

        System.out.println("Min and Max weights");
        try {
            printBuyers(dbController.getMinMaxWeights());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getBuyersForShops() {
        BuyersDao dbController = connect();

        System.out.println("Buyers for shops 2, 4");
        try {
            printBuyers(dbController.getBuyersForShops(new int[]{2, 4}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }
}