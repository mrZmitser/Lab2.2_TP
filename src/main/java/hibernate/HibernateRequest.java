package hibernate;

import data.Buyer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateRequest {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public static List<Buyer> getCityBuyers(String city) throws SQLException {
        List<Buyer> buyers = null;
        try {
            Query<Buyer> query = session.createQuery("from data.Buyer where city='" + city + "'", data.Buyer.class);
            buyers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buyers;
    }


    public static long countBuyersByHeight(Integer height) throws SQLException {
        long buyers = -1;
        try {
            Query<Long> query = session.createQuery("select count(height) FROM data.Buyer WHERE height=" + height,
                    Long.class);
            buyers = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buyers;
    }


    public static long sumFemaleWeights() throws SQLException {
        Long sum = 0L;
        try {
            Query<Long> query = session.createQuery("SELECT SUM(weight) FROM data.Buyer WHERE gender='FEMALE'",
                    Long.class);
            sum = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }


    public static List<Integer> getMinMaxWeight() throws SQLException {
        List<Integer> buyers = new ArrayList<>();
        try {
            Query<Integer> query = session.createQuery("SELECT MIN(weight) FROM data.Buyer",
                    Integer.class);
            buyers.add(query.getSingleResult());
            query = session.createQuery("SELECT MAX(weight) FROM data.Buyer", Integer.class);
            buyers.add(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buyers;
    }


    public static List<Buyer> getAllBuyers() throws SQLException {
        List<Buyer> buyers = new ArrayList<>();
        try {
            Query<Buyer> query = session.createQuery("from data.Buyer GROUP BY id", Buyer.class);
            buyers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buyers;
    }


    public static List<Buyer> getBuyersForShops(Integer[] shops) throws SQLException {
        List<Buyer> buyers = new ArrayList<>();
        try {
            Query<Buyer> query = session.createQuery("from data.Buyer b where b.shop.id=2 or b.shop.id=4", Buyer.class);
            buyers.addAll(query.list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buyers;
    }
}
