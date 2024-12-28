package main.services;

import main.util.HibernateUtil;
import main.models.Customer;
import main.interfaces.CustomerDOA;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class CustomerDOIAImp  implements CustomerDOA {

    private final SessionFactory sessionFactory;

    public CustomerDOIAImp() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Customer customer) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = null;
            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving clients", e);
        }
    }

/*
    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public List<Customer> getAll(Customer customer) {
        return List.of();
    }

    @Override
    public Customer findCustomer(Customer customer) {
        return null;
    }*/
}
