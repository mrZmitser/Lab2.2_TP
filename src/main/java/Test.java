
import java.sql.SQLException;
import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.source.spi.AttributeRole;
import org.hibernate.query.Query;

public class Test {
    public static void main(String[] args) {
        try {
            var buyers = HibernateRequest.getBrestBuyers("Brest").stream().toArray();
            for(var b: buyers)
                System.out.println(b);
            System.out.println(HibernateRequest.countBuyersByHeight(165).toString());
            System.out.println(HibernateRequest.sumFemaleWeights().toString());
            for(var t: HibernateRequest.getMinMaxWeight())
                System.out.println(t.toString());
            for(var t: HibernateRequest.getAllBuyers())
                System.out.println(t.toString());
            for(var t: HibernateRequest.getBuyersForShops())
                System.out.println(t.toString());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}
