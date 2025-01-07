package main.imp;

import main.util.HibernateUtil;
import main.models.Customer;
import main.interfaces.CustomerDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class CustomerDAOImp implements CustomerDAO {

    private final SessionFactory sessionFactory;

    public CustomerDAOImp() {
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
    @Override
    public List<Customer> getAllCustomers(){

        try (Session session = sessionFactory.openSession()){
            Query<Customer> query =session.createQuery("FROM Customer ", Customer.class);
            return query.getResultList();
        }
        catch (Exception e){throw new RuntimeException("Error getting all customers",e);}
    }


    @Override
    public void update(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating customer", e);
        }
    }


    @Override
    public void delete(Customer customer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting customer", e);
        }
    }

}
