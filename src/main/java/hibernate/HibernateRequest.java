package hibernate;

import data.Buyer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateRequest {

    private static Session session;

    public HibernateRequest() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static List<Buyer> getCityBuyers(String city) throws SQLException {
        List<Buyer> buyers = null;
        try {
            Query<Buyer> query = session.createQuery("FROM data.Buyer where city='" + city + "'", data.Buyer.class);
            buyers = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buyers;
    }


    public static int countBuyersByHeight(Integer height) throws SQLException {
        Session session = null;
        Object buyers = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT count(height) FROM data.Buyer WHERE height=" + height);
            buyers = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return -1;
//        return buyers;
    }


    public static Object sumFemaleWeights() throws SQLException {
        Session session = null;
        Object buyers = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT SUM(weight) FROM data.Buyer WHERE gender='FEMALE'");
            buyers = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buyers;
    }


    public static List<Object> getMinMaxWeight() throws SQLException {
        Session session = null;
        List<Object> buyers = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT MIN(weight) FROM data.Buyer");
            buyers.add(query.getSingleResult());
            query = session.createQuery("SELECT MAX(weight) FROM data.Buyer");
            buyers.add(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buyers;
    }


    public static List<Object> getAllBuyers() throws SQLException {
        Session session = null;
        List<Object> buyers = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT surname FROM data.Buyer GROUP BY id");
            buyers = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buyers;
    }


    public static List<Buyer> getBuyersForShops() throws SQLException {
        Session session = null;
        List<Buyer> buyers = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query<Buyer> query = session.createQuery("FROM data.Buyer b INNER JOIN data.Shop s ON b.shop=s.id AND (s.id in 2", Buyer.class);
            buyers.add(query.getSingleResult());
            query = session.createQuery("FROM data.Buyer b INNER JOIN data.Shop s ON b.shop=s.id AND (s.id in 4", Buyer.class);
            buyers.add(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buyers;
    }
}
