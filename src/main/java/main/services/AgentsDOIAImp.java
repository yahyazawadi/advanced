package main.services;

import main.interfaces.AgentsDOA;
import main.models.Agents;
import main.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AgentsDOIAImp implements AgentsDOA {

    private final SessionFactory sessionFactory;

    public AgentsDOIAImp() {
        HibernateUtil hibernateUtil = HibernateUtil.getInstance();
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Agents agents) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(agents);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving agent to the database: " + e.getMessage(), e);
        }
    }
}

