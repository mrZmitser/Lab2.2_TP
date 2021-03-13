package data;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateRequest {
    public static List<Buyer> getBrestBuyers(String city) throws SQLException {
        Session session = null;
        List buyers = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT b.surname FROM data.Buyer b WHERE b.city='" + city + "'");
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

    public static Object countBuyersByHeight(Integer height) throws SQLException {
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
        return buyers;
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
    public static List getAllBuyers() throws SQLException {
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
    public static List getBuyersForShops() throws SQLException {
        Session session = null;
        List<Object> buyers = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("SELECT buyers.id, surname, name, phone_number, credit_card, shop, " +
                    "shop.shop_name FROM data.Buyer b INNER JOIN data.Shop s ON b.shop=s.id AND (s.id in 2");
            buyers.add(query.getSingleResult());
            query = session.createQuery("SELECT buyers.id, surname, name, phone_number, credit_card, shop, " +
                    "shop.shop_name FROM data.Buyer b INNER JOIN data.Shop s ON b.shop=s.id AND (s.id in 4");
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
