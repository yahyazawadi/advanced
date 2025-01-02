package main.services;

import main.interfaces.InquiryDAO;
import main.models.Inquiry;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class InquiryDAOImpl implements InquiryDAO {

    public InquiryDAOImpl() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }
    @Override
    public void addInquiry(Inquiry inquiry) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(inquiry);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Inquiry getInquiryById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Inquiry.class, id);
        }
    }

    @Override
    public List<Inquiry> getAllInquiries() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Inquiry", Inquiry.class).list();
        }
    }

    @Override
    public void updateInquiry(Inquiry inquiry) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(inquiry);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInquiry(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Inquiry inquiry = session.get(Inquiry.class, id);
            if (inquiry != null) {
                session.delete(inquiry);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
