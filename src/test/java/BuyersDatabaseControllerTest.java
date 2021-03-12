import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;

class BuyersDatabaseControllerTest {
    @Test
    void test(){
        BuyersDatabaseController dbController = new BuyersDatabaseController();
        try {
            dbController.connect( "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
            var formatter = new QueriesResultFormatter(16, "|", '*');
            System.out.println("Full Table");
            printStrings(dbController.getFullTable());
            System.out.println(formatter.fillingString(255));
            System.out.println("Buyers from Brest");
            printStrings(dbController.getBuyersByCity("Brest"));
            System.out.println(formatter.fillingString(255));
            System.out.println("Buyers with shops in specified format");
            printStrings(dbController.getBuyersWithShops());
            System.out.println(formatter.fillingString(255));

            System.out.println("Count height = 175");
            printStrings(dbController.countBuyersWithHeight(175));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printStrings(String[] strings) {
        Arrays.stream(strings).forEach(System.out::println);
    }
}