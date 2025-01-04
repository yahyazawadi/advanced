package main.services;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.interfaces.OfferDAO;
import main.models.Offer;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OfferDAOImp implements OfferDAO {
    HibernateUtil hibernateUtil;
    SessionFactory sessionFactory;
    public OfferDAOImp() {
        hibernateUtil=HibernateUtil.getInstance();
        SessionFactory sessionFactory=hibernateUtil.getSessionFactory();
    }
    @Override
    public void save(Offer offer) {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(offer);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace(); // Replace with proper logging
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }





    @Override
    public List<Offer> getAllOffer() {
        return List.of();
    }


    @Override
    public Offer getOfferById(Label id) {
        return null;
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
