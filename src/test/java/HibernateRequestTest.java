import hibernate.HibernateRequest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

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

    @Test
    void generalTest() {
        try {
            HibernateRequest hr = new HibernateRequest();
            var buyers = HibernateRequest.getCityBuyers("Brest");
            for (var b : buyers)
                System.out.println(b.getName());
            /*System.out.println(hibernate.HibernateRequest.countBuyersByHeight(165).toString());
            System.out.println(hibernate.HibernateRequest.sumFemaleWeights().toString());
            for(var t: hibernate.HibernateRequest.getMinMaxWeight())
                System.out.println(t.toString());
            for(var t: hibernate.HibernateRequest.getAllBuyers())
                System.out.println(t.toString());
            for(var t: hibernate.HibernateRequest.getBuyersForShops())
                System.out.println(t.getShop().getShop_name());*/

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}