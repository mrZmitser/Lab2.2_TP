import data.Buyer;
import hibernate.HibernateRequest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

class HibernateRequestTest {
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
   private void printBuyers(List<Buyer> buyers) {
       Arrays.stream(buyers.toArray()).forEach(System.out::println);
   }

    @Test
    void getBuyersByCity() {
        System.out.println("Buyers from Brest");
        try {
            printBuyers(HibernateRequest.getCityBuyers("Brest"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getAllBuyers() {
        System.out.println("Full Table");
        try {
            printBuyers(HibernateRequest.getAllBuyers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    /*@Test
    void getBuyersWithShops() {
        System.out.println("Buyers with shops in specified format");
        try {
            printBuyers(HibernateRequest.getBuyersWithShops());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }*/


    @Test
    void countBuyersWithHeight() {
        System.out.println("Count height = 175");
        try {
            System.out.println(HibernateRequest.countBuyersByHeight(175));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void sumFemaleWeights() {
        System.out.println("Sum female weights");
        try {
            System.out.println(HibernateRequest.sumFemaleWeights());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getMaxMinWeights() {
        System.out.println("Min and Max weights");
        try {
           List<Integer> list = HibernateRequest.getMinMaxWeight();
            System.out.println(list.get(0) + " " + list.get(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));
    }

    @Test
    void getBuyersForShops() {

        System.out.println("Buyers for shops 2, 4");
        try {
            printBuyers(HibernateRequest.getBuyersForShops(new Integer[]{2, 4}));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(QueriesResultFormatter.fillingString(255, '*'));

    }
}