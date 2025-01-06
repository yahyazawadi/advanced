package main.imp;

import main.interfaces.OfferDAO;
import main.models.Offer;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OfferDAOImp implements OfferDAO {
    private final SessionFactory sessionFactory;

    public OfferDAOImp() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Offer offer) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(offer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving offer to the database: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Offer> getAllOffer() {
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery("FROM Offer", Offer.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all offers: " + e.getMessage(), e);
        }
    }

    @Override
    public Offer getOfferById(int id) {
        try (Session session = this.sessionFactory.openSession()) {
            return session.get(Offer.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching offer by ID: " + e.getMessage(), e);
        }
    }
}
