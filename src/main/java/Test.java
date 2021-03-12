
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.source.spi.AttributeRole;

public class Test {
    public static void main(String[] args) {
        try {
            List buyers = getBrestBuyers();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    public static List getBrestBuyers() throws SQLException {
        Session session = null;
        List buyers = new ArrayList();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            BuyersDatabaseController dbController = new BuyersDatabaseController();
            String query = "SELECT * FROM buyers WHERE city='Brest'";
            buyers = (List) session.createSQLQuery(query).list();
        } catch (Exception e) {
            System.out.println("ploho");
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка 'findById'", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return buyers;
    }

}
